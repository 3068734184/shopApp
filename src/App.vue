<script setup lang="ts">
import Menu from './components/Menu.vue'
</script>

<template>
  <div v-if="$route.meta.useFrame != undefined && $route.meta.useFrame == false">
    <RouterView></RouterView>
  </div>
  <div v-if="$route.meta.useFrame == undefined || $route.meta.useFrame == true">
    <el-container>
      <el-header>
        <el-row class="row-bg" justify="space-between">
          <el-col :span="6" class="logo">
            <img src="./assets/logo.jpg" alt="">
          </el-col>
          <el-col :span="6">
            多端服务平台
          </el-col>
          <el-col :span="6">
            <el-dropdown>
              <span class="el-dropdown-link">
                美杜莎
              </span>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>个人中心</el-dropdown-item>
                  <el-dropdown-item>常见问题</el-dropdown-item>
                  <el-dropdown-item>账户与安全</el-dropdown-item>
                  <el-dropdown-item>关于我们</el-dropdown-item>
                  <el-dropdown-item divided command="logout">登出</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Menu></Menu>

        </el-aside>
        <el-main>
          <RouterView />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
* {
  margin: 0px;
  padding: 0px;
}

.el-header {
  width: 100%;
  height: 50px;
  background-color: #c6e2ff;
}

.logo img {
  /* width: 100%; */
  width: 200px;
  height: 50px;
  margin-left: -19px;
}

.el-main {
  padding: 0px;
}

.el-dropdown {
  float: right;
  margin-top: 15px;
  font-weight: 700;
  color: rgb(32, 32, 32);
}

</style>
