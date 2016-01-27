package com.example.project.UnityLiving.Network;


public class Urls {

    public static final int LOGIN_URL_TAG=1000;
    public static final int APARTMNET_LIST_URL_TAG=1001;
    public static final int PENDING_REQUEST_URL_TAG=1002;
    public static final int CHANGE_REQUEST_STATUS_URL_TAG=1003;
    public static final int VISITOR_OUT_TIME_URL_TAG=1004;
    public static final int CREATE_REQUEST_URL_TAG=1005;
    public static final int VISITORS_LIST_URL_TAG=1006;
    public static final int CHANGE_PASSWORD_URL_TAG=1007;


    static final String BASE_URL="http://104.131.83.215:8700/json/";

    public static final String LOGIN_URL=BASE_URL+"login/";
    public static final String APARTMNET_LIST_URL=BASE_URL+"apartmentlist/";
    public static final String PENDING_REQUEST_URL=BASE_URL+"pendingRequests/";
    public static final String CHANGE_REQUEST_STATUS_URL=BASE_URL+"changeRequestStatus/";
    public static final String VISITOR_OUT_TIME_URL=BASE_URL+"pendingRequests/";
    public static final String CREATE_REQUEST_URL=BASE_URL+"pendingRequests/";
    public static final String VISITORS_LIST_URL=BASE_URL+"pendingRequests/";
    public static final String CHANGE_PASSWORD_URL=BASE_URL+"pendingRequests/";


}
