import * as fs from "fs"

interface Gateway {
  publish_rule: Rule[];
  request: Request[];
}

interface Zuul {
  publish_rule: Rule[];
  request: Request[];
}

interface RestTemplate {
  publish_rule: Rule[];
  request: Request[];
}

interface OpenFeign {
  publish_rule: Rule[];
  request: Request[];
}

interface Reactive {
  publish_rule: Rule[];
  request: Request[];
}

interface Rule {
  [ruleName: string]: {
    method: string;
    url: string;
  };
}

interface Request {
  [requestName: string]: {
    method: string;
    url: string;
    header?: {
      key: string;
      value: string;
      type: string;
    };
  };
}

interface MicroserviceGovernanceRouting {
  gateway_example_uri: Gateway[];
  web_client_example_uri: WebClient[];
}

interface WebClient {
  restTemplate: RestTemplate;
  openFeign: OpenFeign;
  reactive: Reactive;
}

interface JSONData {
  microservice_governance_routing_uri: MicroserviceGovernanceRouting[];
}

export function readFile(jsonFilePath: string): string {
  return fs.readFileSync(jsonFilePath, "utf8");
}

export function getConfig(jsonFilePath: string): JSONData {
  const jsonString: string = readFile(jsonFilePath);

  return JSON.parse(jsonString);
}
