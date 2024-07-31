<template>
  <!-- {{ $router.options.routes }} -->
  <el-menu active-text-color="#ffd04b" background-color="#545c64" class="el-menu-vertical-demo"
    :default-active="$route.path" text-color="#fff" :router="true">
    <template v-for="(item, index) in $router.options.routes">
      <el-menu-item :index="item.path" v-if="item.children === undefined && item.meta ? item.meta.show == true : ''">
        <component :is="item.meta ? item.meta.icon : ''" style="width: 20px; height: 20px; margin-right: 5px;"></component>
        <span>{{ item.meta ? item.meta.title : '' }}</span>
      </el-menu-item>

      <el-sub-menu :index="item.path" v-if="item.children != undefined && item.meta ? item.meta.show == true : ''">
        <template #title>
          <component :is="item.meta ? item.meta.icon : ''" style="width: 20px; height: 20px; margin-right: 5px;">
          </component>
          <span>{{ item.meta ? item.meta.title : '' }}</span>
        </template>

        <el-menu-item-group>
          <template v-for="item2 in item.children">
            <el-menu-item :index="item2.path" v-if="item2.meta ? item2.meta.show == true : ''">

              <component :is="item2.meta ? item2.meta.icon : ''" style="width: 20px; height: 20px; margin-right: 5px;">
              </component>
              <p> {{ item2.meta ? item2.meta.title : '' }}</p>

            </el-menu-item>
          </template>
        </el-menu-item-group>
      </el-sub-menu>
    </template>
  </el-menu>

</template>
<script setup lang="ts">

</script>
<style scoped>
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: calc(100vh - 38px);
}
</style>