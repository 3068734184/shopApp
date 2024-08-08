<!-- Home -->
<template>
  <div>
    <el-row :gutter="20" class="topInfo">
      <el-col :span="6">
        <div id="cateNumDiv" class="el-colDiv">
          <div id="ssv1-main-text" class="nowDiv">实时</div>
          <span class="title">类目总数据</span> <br>
          <span class="digital">{{ cateProCount.cateCount }}</span><br>
          <span class="last-span">当前类目名称总记录数</span><br>
        </div>
      </el-col>
      <el-col :span="6">
        <div id="haveTadyDiv" class="el-colDiv">
          <div id="ssv2-main-text" class="nowDiv">实时</div>
          <span class="title">今日新增类目数</span><br>
          <span class="digital">{{ cateProCount.todayCount }}</span><br>
          <span class="last-span">当天类目名称总记录数</span><br>
        </div>
      </el-col>
      <el-col :span="6">
        <div id="proNumDiv" class="el-colDiv">
          <div id="ssv3-main-text" class="nowDiv">实时</div>
          <span class="title">商品总记录数</span><br>
          <span class="digital">{{ cateProCount.proCount }}</span><br>
          <span class="last-span">商品总记录数的统计</span><br>
        </div>
      </el-col>
      <el-col :span="6">
        <div id="protadyDiv" class="el-colDiv">
          <div id="ssv4-main-text" class="nowDiv">实时</div>
          <span class="title">今日新增的商品数量</span><br>
          <span class="digital">{{ cateProCount.proTodayCount }}</span><br>
          <span class="last-span">当天商品名称总记录数</span><br>
        </div>
      </el-col>
    </el-row>
    <!--下方日期和天气-->
    <ContentMain></ContentMain>
  </div>
</template>

<script setup lang='ts'>
import { onMounted, reactive } from "vue"
import { categoryCountApi } from "../api"
import ContentMain from "../components/mainnav/ContentMain.vue";

const cateProCount = reactive({
  cateCount: 0,
  todayCount: 0,
  proCount: 0,
  proTodayCount: 0
})

onMounted(() => {
  CallCateproCountApi()
})

const CallCateproCountApi = () => {
  categoryCountApi.cateCount.call().then((res: any) => {
    console.log(res)
    cateProCount.cateCount = res
  })

  categoryCountApi.todayCount.call().then((res: any) => {
    cateProCount.todayCount = res
  })

  categoryCountApi.proCount.call().then((res: any) => {
    cateProCount.proCount = res
  })
  categoryCountApi.proTodayCount.call().then((res: any) => {
    cateProCount.proTodayCount = res
  })

}
</script>

<style scoped>
.topInfo {
  margin: 0 auto !important;
}

.el-colDiv {
  margin: 20px auto;
  max-width: 300px;
  min-width: 200px;
  overflow: hidden;
  height: 120px;
  border-radius: 10px;
  background-color: black;
  color: aliceblue;
  padding-left: 15px;
  padding-top: 15px;
  position: relative;
  /*定位*/
}

.nowDiv {
  width: 40px;
  height: 19px;
  position: absolute;
  right: 5%;
  font-size: 16px;
  font-weight: 700;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 5px;
}

.title {
  font-size: 17px;
}

.digital {
  font-size: 30px;
  line-height: normal;
  margin-left: 3px;
}

.last-span {
  font-size: 13px;
}


#cateNumDiv {
  background-color: #2e4057;
}

#ssv1-main-text {
  background-color: #1398ff;
}

#haveTadyDiv {
  background-color: #1398ff;
}

#ssv2-main-text {
  background-color: #2e4057;
}

#proNumDiv {
  background-color: #008789;
}

#ssv3-main-text {
  background-color: #ffb400;
}

#protadyDiv {
  background-color: #ffb400;
}

#ssv4-main-text {
  background-color: #008789;
}


.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
