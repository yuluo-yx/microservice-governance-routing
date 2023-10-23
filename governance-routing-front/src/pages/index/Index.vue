<template>
  <div class="index">
    <div class="index-one">
      <h3>Example Request</h3>
      <el-collapse class="collapse" v-model="activeName" accordion>
        <el-collapse-item title="Spring Cloud Gateway Example" name="1">
          <div class="item">
            <el-text class="mx-1" type="primary">添加路由规则</el-text>
            <el-link type="primary">http://localhost:19098/addRule</el-link>
            <el-button type="primary" @click="GatewayAddRule()"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">更新路由规则</el-text>
            <el-link type="primary">http://localhost:19098/updateRule</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">查看可访问的服务</el-text>
            <el-link type="primary">http://localhost:19098/services</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V1 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19098/routing-service-provider/test-a1</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V2 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19098/routing-service-provider/test-a1?id=11</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="Spring Cloud Zuul Example" name="2">
          <div class="item">
            <el-text class="mx-1" type="primary">添加路由规则</el-text>
            <el-link type="primary">http://localhost:19099/addRule</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">更新路由规则</el-text>
            <el-link type="primary">http://localhost:19099/updateRule</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V1 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19099/routing-service-provider/test-a1</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V2 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19099/routing-service-provider/test-a1?id=11</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="OpenFeign Example" name="3">
          <div class="item">
            <el-text class="mx-1" type="primary">添加路由规则</el-text>
            <el-link type="primary">http://localhost:19095/add</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">更新路由规则</el-text>
            <el-link type="primary">http://localhost:19095/update</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V1 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19095/router-test</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V2 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19095/router-test?id=11</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="RestTemplate Example" name="4">
          <div class="item">
            <el-text class="mx-1" type="primary">添加路由规则</el-text>
            <el-link type="primary">http://localhost:19097/addRul</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">更新路由规则</el-text>
            <el-link type="primary">http://localhost:19097/update</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V1 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19097/router-test</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V2 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19097/router-test?id=111</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="Reactive Example" name="5">
          <div class="item">
            <el-text class="mx-1" type="primary">添加路由规则</el-text>
            <el-link type="primary">http://localhost:19096/addRul</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">更新路由规则</el-text>
            <el-link type="primary">http://localhost:19096/update</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V1 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19096/router-test</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">V2 版本服务测试</el-text>
            <el-link type="primary">http://localhost:19096/router-test?id=111</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">查看服务实例</el-text>
            <el-link type="primary">http://localhost:19096/all-services</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
          <div class="item">
            <el-text class="mx-1" type="primary">获取服务节点信息</el-text>
            <el-link type="primary">http://localhost:19096/nodeInfo</el-link>
            <el-button type="primary"> Request </el-button>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="index-two">
      <h3>Application Response</h3>
      <div v-if="show"><el-empty description="点击左侧 request 按钮发送请求，查看标签路由响应" /></div>
      <div v-else>{{responseData}}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import * as gatewayApi from "../../api/governance/gateway"

const responseData = ref("");
const activeName = ref("1");
const show = ref(true)

const setResponseData = (data) => {

  responseData.value = data
}

watch(responseData, (newVal, oldVal) => {

  if (newVal !== "") {

    show.value = false
  }

})

// gateway
const GatewayAddRule = () => {

  // 发送请求
  gatewayApi.gatewayPublishRule().then((res) => {
    
  }).catch((err) => {
    console.log("err: " + err);
  })
}

</script>

<style lang="scss">
.index {
  display: flex;
  flex-wrap: nowrap;
  align-content: center;
  justify-content: space-evenly;
  align-items: stretch;
  flex-direction: row;
  h3 {
    font-size: 19px;
    font-weight: 700;
    font-family: "Courier New", Courier, monospace;
  }
}

.index-one {
  width: 50em;
  border-radius: 25px;
  border: 2px solid #8ac007;
  padding: 20px;

  .el-collapse-item__header {
    background-color: #808080;
  }
  .el-collapse-item__content {
    background-color: #ffffff;
  }

  .item {
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: stretch;
    justify-content: space-between;
    align-items: baseline;
    margin-left: 1em;
    margin-right: 1em;

    .el-button {
      margin-top: 0.3em;
    }
  }
}

.index-two {
  width: 35em;
  border-radius: 25px;
  border: 2px solid #8ac007;
  padding: 20px;
}
</style>
