package indi.yuluo.governance.commons.governance.auth.rule;

import java.util.List;
import java.util.Map;

/**
 * @author yuluo-yx
 * @author <a href="1481556636@qq.com"></a>
 */

public class JwtRule {

	private String name;

	private Map<String, String> fromHeaders;

	private String issuer;

	private List<String> audiences;

	private String jwks;

	private List<String> fromParams;

	private String outputPayloadToHeader;

	private boolean forwardOriginalToken;

	public JwtRule() {

	}

	public JwtRule(String name, Map<String, String> fromHeaders, String issuer,
			List<String> audiences, String jwks, List<String> fromParams,
			String outputPayloadToHeader, boolean forwardOriginalToken) {
		this.name = name;
		this.fromHeaders = fromHeaders;
		this.issuer = issuer;
		this.audiences = audiences;
		this.jwks = jwks;
		this.fromParams = fromParams;
		this.outputPayloadToHeader = outputPayloadToHeader;
		this.forwardOriginalToken = forwardOriginalToken;
	}

	public String getName() {
		return name;
	}

	public Map<String, String> getFromHeaders() {
		return fromHeaders;
	}

	public String getIssuer() {
		return issuer;
	}

	public List<String> getAudiences() {
		return audiences;
	}

	public String getJwks() {
		return jwks;
	}

	public List<String> getFromParams() {
		return fromParams;
	}

	public String getOutputPayloadToHeader() {
		return outputPayloadToHeader;
	}

	public boolean isForwardOriginalToken() {
		return forwardOriginalToken;
	}

}
