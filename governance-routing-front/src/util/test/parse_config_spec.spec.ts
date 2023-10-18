import { log } from "console";
import { getConfig } from "../src/parseConfig.ts";

function testGetConfig() {
  const config = getConfig("../governance-example-url.json");

  iterateJson(config);
}

function iterateJson(data: any, parentKey?: string) {
  for (const key in data) {
    // log(key)
    if (Array.isArray(data[key])) {
      data[key].forEach((item: any) => iterateJson(item, key));
    } else if (typeof data[key] === "object") {
      iterateJson(data[key], key);
    } else {
      const dataName = parentKey ? `${parentKey}.${key}` : key;
      console.log(`${dataName}: ${data[key]}`);
    }
  }
}

testGetConfig();
