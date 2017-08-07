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
      "ParcelReturnInitiated" : "10"
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
        "PickUpRequestReceived" : "10",
        "AtCarrierSortCenter" : "20",
        "AtWarehouse" : "30",
        "OutForDelivery" : "40",
        "OrderDelivered" : "50",
        "SortErrorAtCarrier" : "20"
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
        "ReturnCheckedIn" : "60",
        "ReturnRecievedIntact" : "70",
        "ReturnReceivedDamaged" : "70",
    }
},{
	  "_id" : "RFS",
	  "statusMap" : {
	      "RETURN_DESPATCH" : {
	          "trackingStatusCode" : "RETURN_DESPATCH",
	          "description" : "Parcel has been despatched from store"
	      }
	  },
	  "eventSequence" : {
	      "RETURN_DESPATCH" : "10"
	  }
	}
],
{ writeConcern: { w: "majority", wtimeout: 5000 } }
);
