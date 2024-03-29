package indi.yuluo.governance.routing.constant;

/**
 * @author yuluo
 * @author <a href="1481556636@qq.com"></a>
 */

public final class LabelRoutingConstants {

	private LabelRoutingConstants() {
	}

	/**
	 * Support Parsing Rules from path,only URI at present.
	 */
	public static final String PATH = "path";

	/**
	 * Support Parsing Rules from header.
	 */
	public static final String HEADER = "header";

	/**
	 * Support Parsing Rules from parameter.
	 */
	public static final String PARAMETER = "parameter";

	/**
	 * Filter base on version metadata.
	 */
	public static final String VERSION = "version";

	/**
	 * Default.
	 */
	public static final String DEFAULT = "default";

	/**
	 * Sign of no match any rule.
	 */
	public static final int NO_MATCH = -1;

	/**
	 * Avoid loss of accuracy.
	 */
	public static final double KEEP_ACCURACY = 1.0;

	/**
	 * Listener execution order.
	 */
	public static final int LISTENER_ORDER = 200;

	/**
	 * Unknown.
	 */
	public static final String UNKNOWN = "unknown";

	/**
	 * Ignored.
	 */
	public static final String IGNORED = "ignored";

	/**
	 * Zone.
	 */
	public static final String ZONE = "zone";

	/**
	 * Region.
	 */
	public static final String REGION = "region";

	/**
	 * Service governance traffic stains the public prefix.
	 */
	public static final String PROPERTY_PREFIX = "spring.cloud.governance.routing";

	/**
	 * Turn on the zone avoidance rule.
	 */
	public static final String ZONE_AVOIDANCE_RULE_ENABLED = PROPERTY_PREFIX
			+ ".zone.affinity.enabled";

	/**
	 * Turn on the RestTemplate interceptor.
	 */
	public static final String REST_INTERCEPT_ENABLED = PROPERTY_PREFIX
			+ ".rest.intercept.enabled";

	/**
	 * Turn on the Feign interceptor.
	 */
	public static final String FEIGN_INTERCEPT_ENABLED = PROPERTY_PREFIX
			+ ".feign.intercept.enabled";

	/**
	 * Turn on the WebClient interceptor.
	 */
	public static final String REACTIVE_INTERCEPT_ENABLED = PROPERTY_PREFIX
			+ ".reactive.intercept.enabled";

	/**
	 * The feign request header is passed at the beginning.
	 */
	public static final String FEIGN_HEADER_TRANSMISSION_ENABLED = PROPERTY_PREFIX
			+ ".feign.core.header.transmission.enabled";

	/**
	 * The rest request header is passed at the beginning.
	 */
	public static final String REST_HEADER_TRANSMISSION_ENABLED = PROPERTY_PREFIX
			+ ".rest.template.core.header.transmission.enabled";

	/**
	 * The reactive request header is passed at the beginning.
	 */
	public static final String WEB_CLIENT_HEADER_TRANSMISSION_ENABLED = PROPERTY_PREFIX
			+ ".web.client.core.header.transmission.enabled";

	/**
	 * Local Service Availability Zone staining constants.
	 */
	public static final String SCA_ROUTING_SERVICE_ZONE = "s-l-r-service-zone";

	/**
	 * Local Service region staining constants.
	 */
	public static final String SCA_ROUTING_SERVICE_REGION = "s-l-r-service-region";

	/**
	 * Other Service Availability Zone staining constants.
	 */
	public static final String SCA_ROUTING_ZONE = "s-l-r-zone";

	/**
	 * Other Service region staining constants.
	 */
	public static final String SCA_ROUTING_REGION = "s-l-r-region";

	/**
	 * Pass the request header priority constant.
	 */
	public static final String SERVICE_HEADER_PRIORITY = PROPERTY_PREFIX
			+ ".service.header.priority";

	/**
	 * Empty String constants.
	 */
	public static final String EMPTY_STRING = "";

}
