package com.indix.distributed.primary.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfiguration {

  @Value("${kafka.broker.address}")
  private String kafkaBrokerAdress;

  @Value("${kafka.tracking.event.topic}")
  private String trackingEventTopic;

  @Value("${kafka.vehicle.event.topic}")
  private String vehicleEventTopic;

  @Value("${kafka.publisher.dhl.event.topic}")
  private String publisherDhlTopic;

  @Value("${kafka.logistic.unit.event.topic}")
  private String logisticEventTopic;

  @Value("${kafka.geolocation.event.topic}")
  private String geoLocationEventTopic;

  @Value("${identity.url}")
  private String identityServiceURL;

  @Value("${identity.clientid}")
  private String identityClientId;

  @Value("${identity.enabled}")
  private boolean identityEnabled;

  @Value("${identity.skipAuth.domains}")
  private String identitySkipAuthDomains;

  @Value("${ehcache.xml}")
  private String ehCacheXml;

  @Value("${mongodb.replicaset}")
  private String replicaSet;

  @Value("${mongodb.dbname}")
  private String dbName;

  @Value("${mongodb.username}")
  private String userName;

  @Value("${mongodb.password}")
  private String password;

  public String getGeoLocationEventTopic() {
    return geoLocationEventTopic;
  }

  public void setGeoLocationEventTopic(String geoLocationEventTopic) {
    this.geoLocationEventTopic = geoLocationEventTopic;
  }

  public String getPublisherDhlTopic() {
    return publisherDhlTopic;
  }

  public void setPublisherDhlTopic(String publisherDhlTopic) {
    this.publisherDhlTopic = publisherDhlTopic;
  }

  public String getKafkaBrokerAdress() {
    return kafkaBrokerAdress;
  }

  public void setKafkaBrokerAdress(String kafkaBrokerAdress) {
    this.kafkaBrokerAdress = kafkaBrokerAdress;
  }

  public String getIdentityServiceURL() {
    return identityServiceURL;
  }

  public void setIdentityServiceURL(String identityServiceURL) {
    this.identityServiceURL = identityServiceURL;
  }

  public String getIdentityClientId() {
    return identityClientId;
  }

  public void setIdentityClientId(String identityClientId) {
    this.identityClientId = identityClientId;
  }

  public boolean isIdentityEnabled() {
    return identityEnabled;
  }

  public void setIdentityEnabled(boolean identityEnabled) {
    this.identityEnabled = identityEnabled;
  }

  public String getIdentitySkipAuthDomains() {
    return identitySkipAuthDomains;
  }

  public void setIdentitySkipAuthDomains(String identitySkipAuthDomains) {
    this.identitySkipAuthDomains = identitySkipAuthDomains;
  }

  public String getTrackingEventTopic() {
    return trackingEventTopic;
  }

  public void setTrackingEventTopic(String trackingEventTopic) {
    this.trackingEventTopic = trackingEventTopic;
  }

  public String getVehicleEventTopic() {
    return vehicleEventTopic;
  }

  public void setVehicleEventTopic(String vehicleEventTopic) {
    this.vehicleEventTopic = vehicleEventTopic;
  }

  public String getLogisticEventTopic() {
    return logisticEventTopic;
  }

  public void setLogisticEventTopic(String logisticEventTopic) {
    this.logisticEventTopic = logisticEventTopic;
  }

  public String getEhCacheXml() {
    return ehCacheXml;
  }

  public void setEhCacheXml(String ehCacheXml) {
    this.ehCacheXml = ehCacheXml;
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
