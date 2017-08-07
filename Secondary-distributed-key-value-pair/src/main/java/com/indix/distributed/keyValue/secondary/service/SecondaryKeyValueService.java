package com.indix.distributed.keyValue.secondary.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.indix.distributed.common.model.ErrorInfo;
import com.indix.distributed.common.model.KeyValuePair;
import com.indix.distributed.keyValue.common.exception.DataValidationException;
import com.indix.distributed.keyValue.secondary.dao.SecondaryKeyValueDAO;

@Component
public class SecondaryKeyValueService {

	@Autowired
	private SecondaryKeyValueDAO secondaryKeyValueDAO;

	private static final Logger LOGGER = LoggerFactory.getLogger(SecondaryKeyValueService.class);

	public KeyValuePair getValue(final String key)  {
		LOGGER.info("Processing event. {}", key);
		return secondaryKeyValueDAO.getValue(key);
	}

	public void processEvent(final KeyValuePair keyValuePair) throws DataValidationException {
		LOGGER.info("Processing event. {}", keyValuePair);
		List<ErrorInfo> errorInfos  = validateEvent(keyValuePair);
		if(CollectionUtils.isEmpty(errorInfos)){
			secondaryKeyValueDAO.save(keyValuePair);
		} else {
			throw new DataValidationException("Insufficient Data ",errorInfos);
		}
	}

	private List<ErrorInfo> validateEvent(KeyValuePair keyValuePair) {
		List<ErrorInfo> errorInfos = null;
		boolean isValidEvent = checkMandatoryParams(keyValuePair);
		if (!isValidEvent) {
			errorInfos = new ArrayList<>();
			ErrorInfo errorInfo = new ErrorInfo("Mandatory parameters missing", "400");
			errorInfos.add(errorInfo);
		}
		return errorInfos;

	}

	private boolean checkMandatoryParams(KeyValuePair keyValuePair) {

		if (keyValuePair.getKey()==null) {
			return false;
		}
		return true;
	}

}
