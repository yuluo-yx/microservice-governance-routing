<template>
    <div>
        <div>
            <a href="#"><el-image src="src/assets/MICROSERVICES_Logo.png"></el-image></a>
        </div>
        <div class="title">
            <el-menu default-active="2" class="el-menu-vertical-demo" router>
                <template v-for="value in router.options.routes[0].children" :key="value.path">
                    <el-menu-item v-show="value.meta?.isShow == true" :index="value.path">
                        <el-icon>
                            <component :is="value.meta?.icon"></component>
                        </el-icon>
                        <span>{{ value.meta?.title }}</span>
                    </el-menu-item>
                </template>
            </el-menu>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";
import {
    ArrowRight
} from '@element-plus/icons-vue'

const router = useRouter();

const logout = () => {
    console.log("logout");

    localStorage.removeItem("token")
    router.push("/login")
}

</script>
  
<style lang="scss" scoped>
.el-image {
    height: 70px;
    width: 190px;
    margin-left: -10px;
    margin-top: -5px;
}

.title {
    float: left;
    margin-top: -9.5vh;
    margin-left: 12vw;
    font-size: 18px;
    font-weight: 600;
    color: rgb(64, 160, 255);
    line-height: 2vh;

    ul li {
        float: left;
        list-style: none // margin-right: 2vw
    }
}

.right {
    font-weight: 600;
    float: right;
    margin-right: 3vw;
    margin-top: -6.5vh;
    vertical-align: top;

    .el-button {
        margin-top: -5px;
    }
}
</style>