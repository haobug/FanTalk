package project.fantalk.api.tencent;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TencentApi;
import org.scribe.model.SignatureType;
import org.scribe.oauth.OAuthService;

import project.fantalk.api.ReturnCode;
import project.fantalk.api.Utils;
import project.fantalk.api.common.oauth.AbstractOAuth;
import project.fantalk.model.Member;

public class TencentServiceOAuth extends AbstractOAuth {
	private static final Logger logger = Logger
			.getLogger(TencentServiceOAuth.class.getName());
	
	private static final OAuthService TencentOAuthService = new ServiceBuilder()
			.provider(TencentApi.class).apiKey(TencentConstant.apiKey)
			.apiSecret(TencentConstant.secret)
			.signatureType(SignatureType.QueryString).build();
	
	public TencentServiceOAuth(String username, String password) {
		super(username, password);
	}

	public TencentServiceOAuth(Member member) {
		super(member.getTencentUsername(), member.getTecentPassword());
	}

	@Override
	public Member processMember(Member member) {
		member.setTencentUsername(getUsername());
		member.setTecentPassword(getPassword());
		member.put();
		return member;
	}

	@Override
	public String getApiKey() {
		return TencentConstant.apiKey;
	}

	@Override
	public String getApiSecret() {
		return TencentConstant.secret;
	}

	@Override
	public String getSource() {
		return TencentConstant.apiKey;
	}

	@Override
	public String getSNSName() {
		return "腾讯微博";
	}

	@Override
	public OAuthService getOAuthService() {
		return TencentOAuthService;
	}
	
	public ReturnCode update(String text) {
		String params = "format=json&content=" + Utils.encode(text)
				+ "&clientip=72.14.203.141";
		String data = doPost(API.UPDATE_STATUS.url(), params);
		return getActionReturnCode(data);
	}

	public ReturnCode verify() {
		String params = "format=json";
		String data = doGet(API.VERIFY_ACCOUNT.url(), params);
		return getActionReturnCode(data);
	}
	
	private ReturnCode getActionReturnCode(String data) {
		if (!Utils.isEmpty(data)) {
			try {
				logger.info(data);
				JSONObject o = new JSONObject(data);
				logger.info(o.toString());
				if (o.has("msg") && "ok".equalsIgnoreCase(o.getString("msg"))) {
					return ReturnCode.ERROR_OK;
				}
			} catch (JSONException e) {
				logger.log(Level.WARNING, e.getMessage(), e);
			}
		}
		return ReturnCode.ERROR_FALSE;
	}

	/**
	 * 枚举，腾讯微博API方法接口URL列表
	 * 
	 * @author mcxiaoke
	 */
	static enum API {
		UPDATE_STATUS("t/add"), // POST 发布消息

		VERIFY_ACCOUNT("user/info"), // GET 验证帐号密码
		;

		private String value;
		private static final String API_URL = "http://open.t.qq.com/api/";// 腾讯API地址
		private static final String JSON = "";// JSON格式

		API(String value) {
			this.value = value;
		}

		public String url() {
			return new StringBuilder().append(API_URL).append(value).append(
					JSON).toString();
		}
	}
}
