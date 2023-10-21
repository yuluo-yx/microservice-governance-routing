<template>
  <div class="index">
    <div class="index-one">
      <h3>Example Request</h3>
      <el-collapse class="collapse" v-model="activeName" accordion>
        <el-collapse-item title="Spring Cloud Gateway Example" name="1">
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="Spring Cloud Zuul Example" name="2">
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="OpenFeign Example" name="3">
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="RestTemplate Example" name="4">
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
        </el-collapse-item>
        <el-collapse-item title="Reactive Example" name="5">
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
          <div class="item">
            <el-link type="primary">primary</el-link>
            <el-button type="primary"> request </el-button>
          </div>
        </el-collapse-item>
      </el-collapse>
    </div>
    <div class="index-two">
      <h3>Application Response</h3>
      <p v-if="responseData.value">{{responseData}}</p>
      <el-empty v-else description="点击左侧 request 按钮发送请求，查看标签路由响应" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const responseData = ref<String>("响应");
const activeName = ref("1");

// onMounted(() => {
  
// })

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
  width: 40em;
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
    display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    align-content: stretch;
    justify-content: space-evenly;
    align-items: flex-end;

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
