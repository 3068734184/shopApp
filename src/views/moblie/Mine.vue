<!-- mobileMine -->
<template>
  <div>
    <div>
      <van-nav-bar title="我的" left-arrow @click-left="onClickLeft" />
    </div>

    <div class="conent-linear">
      <div class="header">
        <div class="user-info">
          <div class="avatar-wrap">
            <div class="avatar">
              <div class="avatar-image">
                <img style="    min-width: 1px; min-height: 1px; border-radius: 50%; width: 100%; height: auto;"
                  src="https://storage.360buyimg.com/i.imageUpload/6a645f616351744e42696a4d43677631363535343732333234393434_mid.jpg"
                  alt="">
              </div>
            </div>
          </div>
          <div class="personal-wrap">
            <div class="name-wrap">
              <span class="header-name">{{ username }}</span>
            </div>
            <div class="honor-tips">
              <div class="tips-left"></div>
              <div class="tips-right">开启小白守约特权</div>
            </div>
          </div>
        </div>
        <div class="account-setting">
          <van-icon name="setting-o" />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang='ts'>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

// 返回键
const router = useRouter()
const onClickLeft = () => {
  router.push({ path: "/moveshow/index" })
}

const username = ref('京东用户')

onMounted(() => {
  const getUsername = sessionStorage.getItem('username')
  if (getUsername != null) {
    username.value = getUsername
  }
})
</script>

<style scoped>
.conent-linear {
  background: url(https://img12.360buyimg.com/img/s750x432_jfs/t1/84804/29/26776/112106/624d07d0E55603649/07ed8923a7bdbad2.png) no-repeat scroll 0 0 / 100% auto;
}

.header {
  position: relative;
  overflow: hidden;
  width: 100%;
  box-sizing: border-box;
  padding-top: 19px;
}

.user-info {
  padding: 0px 0px 10px 18px;
  display: flex;
  align-items: center;
  position: relative;
}

.avatar-wrap {
  position: relative;
}

.avatar {
  width: 52px;
  height: 52px;
  overflow: hidden;
  box-shadow: 0 4px 8px 0 rgba(250, 44, 25, .08);
  border-radius: 60px;
  transform: translateZ(0);
  box-sizing: border-box;
  border: 1px solid #fff;
}

.avatar-image {
  display: inline-block;
  overflow: hidden;
  position: relative;
}

.personal-wrap {
  flex: 1;
  padding: 7px 0 0 8px;
  position: relative;
}

.name-wrap {
  font-size: 18px;
  display: flex;
  align-items: center;
  line-height: 1.2;
}

.header-name {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 18px;
  font-weight: 500;
}

.honor-tips {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin-top: 10px;
}

.tips-left {
  background: url(https://img12.360buyimg.com/img/jfs/t1/228437/38/18293/2904/665ef093Fbc0ae2a5/360edecae4800d82.png) center center / contain no-repeat;
  width: 78.5px;
  height: 18.5px;
}

.tips-right {
  position: relative;
  padding-right: 14px !important;
  max-width: 150px;
  height: 16px;
  line-height: 17px;
  padding: 0 14px 0 6px;
  margin: 0 6px 0 0;
  border-radius: 15px;
  font-size: 10px;
  background: hsla(0, 0%, 100%, .68);
  border: 1px solid #f8cfce;
  text-align: center;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-weight: 400;
}

.account-setting {
  position: absolute;
  right: 18px;
  top: 40px;
  height: 35px;
  line-height: 40px;
  overflow: hidden;
}
</style>
