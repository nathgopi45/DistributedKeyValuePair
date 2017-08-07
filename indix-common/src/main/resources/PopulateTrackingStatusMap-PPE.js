use querystore;
db.createCollection("tracking-agent-status-map")
db["tracking-agent-status-map"].insert(
[
{
  "_id" : "OFS",
  "statusMap" : {
      "returnBooked" : {
          "trackingStatusCode" : "ParcelReturnInitiated",
          "description" : "Parcel return journey has been booked"
      }
  },
  "eventSequence" : {
      "ParcelReturnInitiated" : "1"
  }
},
{
    "_id" : "CMS",
    "statusMap" : {
        "CMS-Col-04" : {
            "trackingStatusCode" : "PickUpRequestReceived",
            "description" : "request to pick the parcel from ship from location is received"
        },
        "CMS-InTr-01" : {
            "trackingStatusCode" : "AtCarrierSortCenter",
            "description" : "parcel is getting sorted at the carrier sort center"
        },
        "CMS-InTr-06" : {
            "trackingStatusCode" : "AtWarehouse",
            "description" : "parcel is at the carrier warehouse"
        },
        "CMS-InTr-04" : {
            "trackingStatusCode" : "OutForDelivery",
            "description" : "parcel is out for delivery by the carrier"
        },
        "CMS-Del-01" : {
            "trackingStatusCode" : "OrderDelivered",
            "description" : "parcel delivered at the ship to location"
        },
        "CMS-IntrD-01" : {
            "trackingStatusCode" : "SortErrorAtCarrier",
            "description" : "Prime sort error at the carrier sort center"
        }
    },
    "eventSequence" : {
        "PickUpRequestReceived" : "1",
        "AtCarrierSortCenter" : "2",
        "AtWarehouse" : "3",
        "OutForDelivery" : "4",
        "OrderDelivered" : "5",
        "SortErrorAtCarrier" : "2"
    }
},
{
    "_id" : "RWH",
    "statusMap" : {
        "CheckedIn" : {
            "trackingStatusCode" : "ReturnCheckedIn",
            "description" : "Parcel has been checked in at National return center"
        },
        "ReceivedIntact" : {
            "trackingStatusCode" : "ReturnRecievedIntact",
            "description" : "return parcel has been recieved intact at the warehouse"
        },
         "ReceivedDamaged" : {
            "trackingStatusCode" : "ReturnReceivedDamaged",
            "description" : "return parcel has been recieved in Damaged condition at the warehouse"
        },
        "UnPreadvisedIntact" : {
            "trackingStatusCode" : "ReturnRecievedIntact",
            "description" : "An unpreadvised parcel has been recieved in Intact condition at the warehouse"
        },
        "UnPreadvisedDamaged" : {
            "trackingStatusCode" : "ReturnReceivedDamaged",
            "description" : "An unpreadvised parcel has been recieved in Damaged condition at the warehouse"
        }
        
    },
    "eventSequence" : {
        "ReturnCheckedIn" : "6",
        "ReturnRecievedIntact" : "7",
        "ReturnReceivedDamaged" : "7",
    }
}
],
{ writeConcern: { w: "majority", wtimeout: 5000 } }
);
