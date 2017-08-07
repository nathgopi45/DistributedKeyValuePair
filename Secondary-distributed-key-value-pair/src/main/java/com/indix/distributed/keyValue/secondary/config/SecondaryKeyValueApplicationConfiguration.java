package com.indix.distributed.keyValue.secondary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecondaryKeyValueApplicationConfiguration {

	@Value("${kafka.broker.address}")
	private String kafkaBrokerAdress;

	@Value("${kafka.zookeeper.address}")
	private String kafkaZooKeeperAdress;

	@Value("${mongodb.replicaset}")
	private String replicaSet;

	@Value("${mongodb.dbname}")
	private String dbName;

	@Value("${mongodb.username}")
	private String userName;

	@Value("${mongodb.password}")
	private String password;

	public String getKafkaBrokerAdress() {
		return kafkaBrokerAdress;
	}

	public void setKafkaBrokerAdress(String kafkaBrokerAdress) {
		this.kafkaBrokerAdress = kafkaBrokerAdress;
	}

	public String getKafkaZooKeeperAdress() {
		return kafkaZooKeeperAdress;
	}

	public void setKafkaZooKeeperAdress(String kafkaZooKeeperAdress) {
		this.kafkaZooKeeperAdress = kafkaZooKeeperAdress;
	}

	public String getReplicaSet() {
		return replicaSet;
	}

	public void setReplicaSet(String replicaSet) {
		this.replicaSet = replicaSet;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
