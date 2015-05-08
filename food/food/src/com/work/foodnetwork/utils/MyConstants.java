package com.work.foodnetwork.utils;

public class MyConstants {

	public interface ApiHandleCode {

		public static final int UNKNOW_ERROR = 001;
		public static final int OK = 200;
		public static final int NULL_OR_EMPTY_RESPONSE_ERROR = 201;
		public static final int NO_NETWORK = 202;
		public static final int INVALID_USERNAME_PASSWORD = 203;
		public static final int PARSE_JSON_ERROR = 204;
		public static final int API_RESPONSE_ERROR = 205;
	}

	public interface ApiResponseCode {
		public static final int API_TOKEN_ERROR = 1300;
		public static final int ERROR_GENERAL = 1000;
	}

	public interface ApiJsonTag {

		public static final String MESSAGE = "message";
		public static final String ERROR = "error";
		public static final String STATUS = "status";
		public static final String CODE = "code";
		public static final String USER_INFO = "user_info";
		public static final String API_TOKEN = "API_token";
		public static final String TYPES = "types";
		public static final String PLIS_DATA = "plisData";
		public static final String VERSION = "version";
		public static final String DATA_TO_SYNC = "dataToSync";
		public static final String EXPEDITEUR_DATA = "expediteurData";
		public static final String DESTINATAIRE_DATA = "destinataireData";
		public static final String TRANSPORTEUR_DATA = "transporteurData";
		public static final String USER_DATA = "userData";
	}

}
