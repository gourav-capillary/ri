package com.orion.ri.api.network;

public interface ServerResponseCode {

    int SUCCESS = 200;
    int TOGGLE_SUCCESS = 0;
    int INSURANCE_SUCCESS = 700;
    int INTERNET_ERROR = 490;
    int SOME_THING_WENT_WRONG = 0;
    int BLOCK_USER = 1534;
    int EMAIL_BLOCK_USER = 517;
    int INVALID_SESSION = 1505;
    int REWARD_ISSUE_ERROR = 618;
    int REWARD_ISSUE = 8003;
    int MARVEL_PARTNER_ERROR = 8004;
    int MARVEL_PARTNER_ERROR_BL = 1010;
    int INVALID_OTP_CODE = 401;
    int CARD_ERROR = 3010;
    int CODE_IS_USED = 747;
    int CODE_IS_USED_TWO = 711;
    int KRIS_FLYER_ERROR = 8007;
    int PROMO_TOGGLE_LOCKED = 737;

    int SERVER_ERROR = 500;
    int SESSION_EXPIRED = 440;
    int RATE_LIMIT_EXCEEDED = 429;
    int UNHENDLED_EXCEPTION = 1000;
    int AUTH_FAILED = 1003;
    int NO_RECORD_FOUND = 1009;
    int UPDATE_SUCCESS = 1007;

//1004	Successful
//1007	Updated successfully
//1008	Update failed
//1009	No record found
//1011	Order is not ready for shipping
//1012	Username is required
//1013	Voucher name is required
//1014	Voucher already exists
//1015	Invalid campaign id
//1016	Invalid input
//1017	User does not exist
//1018	Data update added to the task queue. You can view the status of the update in the Control Panel. An email will be sent to the merchant’s registered email id after the task is completed.
//1019	Data update added to the task queue. You can view the status of update using ‘GetProductInformation’
//            1020	No common shipping
//1021	Warning
//1022	Products not available
//1023	Invalid city. Please select a valid address
//1024	PIN code is not serviceable for the selected location
//1025	PIN code not serviceable for the given address
//1027	Update failed
//1028	Invalid page number
//1030	Service is not authorized
//1031	Your account approval is still in process. You cannot access your account now
//1032	Your account has been locked because of multiple failed login attempts. Please contact customer support.
//            1033	Invalid inputs provided. Please provide correct input
//1034	Product is added already
//1035	Shipment created already
//1036	No default self return found
//1037	Your account has been locked because of multiple failed login attempts. Please contact customer support.
//            1038	Invalid OTP
//1039	Your password has been expired. Please reset the password
//1040	Invalid password
//1041	No user account exists with the provided details
//1042	Your account has not been activated yet. Please try later
//1043	Order is already authorized
//1044	Invalid delivery slot
//1045	Invalid merchant id
//1046	Invalid location id
//5001	Invalid user information
//5001	No Bundle items present for the specified product id
//5002	Invalid Delivery Mode: {DeliveryMode} for Product as IsShip/IsOnline/Instore Pickup is not set
//5003	Invalid Delivery Mode: {DeliveryMode} for Location Id: {LocationId}
//5004	ProductId is not available at the specified locationId
//5005	Product with the specified ProductId is not available
//5006	Product with the specified ProductId has type 'A’ which is not allowed. You cannot add an add-on product directly
//            5007	Invalid variant ProductId
//6220	Invalid ETA start/end time
//6221	Invalid ETA range
//6222	Invalid ETA units
//6223	Invalid end range
//6224	End date is greater than start date
//6225	Invalid ETA details
//6226	No location code passed
//6227	Invalid location
//6228	Invalid ETA
}