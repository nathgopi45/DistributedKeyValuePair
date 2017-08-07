package com.indix.distributed.primary.core.config;

import static java.util.Arrays.asList;
import static java.util.Base64.getDecoder;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

@Configuration
public class MongoConfig {

	@Autowired
	private AppConfiguration applicationConfiguration;

	public @Bean MongoClientFactoryBean mongoFactory() {
		MongoClientFactoryBean factoryBean = new MongoClientFactoryBean();
		if (applicationConfiguration.getReplicaSet() != null) {
			List<ServerAddress> addressList = new ArrayList<ServerAddress>();
			asList(applicationConfiguration.getReplicaSet().split("\\,")).stream().forEach(replicaSet -> {
				String[] serverPortArray = replicaSet.split("\\:");
				addressList.add(new ServerAddress(
						new InetSocketAddress(serverPortArray[0], Integer.parseInt(serverPortArray[1]))));
			});
			factoryBean.setReplicaSetSeeds(addressList.toArray(new ServerAddress[addressList.size()]));
		}
		MongoClientOptions options = MongoClientOptions.builder().readPreference(ReadPreference.secondary())
				.connectTimeout(30000).build();
		factoryBean.setMongoClientOptions(options);
		if (StringUtils.isNoneBlank(applicationConfiguration.getUserName(), applicationConfiguration.getPassword())) {
			MongoCredential[] credentials = new MongoCredential[1];
			MongoCredential credential = MongoCredential.createCredential(applicationConfiguration.getUserName(),
					applicationConfiguration.getDbName(), getDecodedPassword().toCharArray());
			credentials[0] = credential;
			factoryBean.setCredentials(credentials);
		}
		return factoryBean;
	}

	private String getDecodedPassword() {
		return new String(getDecoder().decode(applicationConfiguration.getPassword().getBytes()));
	}

	public @Bean MongoTemplate mongoTemplate(Mongo mongoFactory) {
		return new MongoTemplate(mongoFactory, applicationConfiguration.getDbName());
	}
	
}
