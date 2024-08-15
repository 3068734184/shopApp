<!-- Login -->
<template>
    <div>
        <div>
            <van-nav-bar title="登录注册" left-arrow @click-left="onClickLeft" />
        </div>

        <van-form v-if="formShow" style="margin-top: 30px;" @submit="loginSubmit({ ltel, lpassword })">
            <van-cell-group inset>
                <van-field v-model="ltel" type="tel" clearable placeholder="请输入手机号"
                    :rules="[{ pattern, message: '请输入正确的国内手机号' }]" />
                <van-field v-model="lpassword" type="password" clearable placeholder="请输入密码"
                    :rules="[{ validator, message: '至少6位且至少包含一个英文字母' }]">
                    <template #button>
                        <van-button type="primary" size="small">忘记密码</van-button>
                    </template>
                </van-field>
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    登录
                </van-button>
            </div>
            <p><span style="float: right;color: rgba(0, 0, 0, 0.4); font-size: 14px; margin-right: 20px;"
                    @click="switchForm">手机快速注册</span></p>
        </van-form>
        <van-form v-if="!formShow" style="margin-top: 30px;" @submit="registerSubmit({ rtel, rpassword })">
            <van-cell-group inset>
                <van-field v-model="rtel" type="tel" clearable placeholder="请输入手机号"
                    :rules="[{ pattern, message: '请输入正确的国内手机号' }]" />
                <van-field v-model="rpassword" type="password" clearable placeholder="请输入密码"
                    :rules="[{ validator, message: '至少6位且至少包含一个英文字母' }]">
                </van-field>
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    注册
                </van-button>
            </div>
            <p><span style="float: right;color: rgba(0, 0, 0, 0.4); font-size: 14px; margin-right: 20px;"
                    @click="switchForm">已有账号登录</span></p>
        </van-form>


    </div>
</template>

<script setup lang='ts'>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { userApi } from '../../api';
import { showFailToast } from 'vant';

// 返回键
const router = useRouter()
const onClickLeft = () => {
    router.go(-1)
}

const ltel = ref('');
const lpassword = ref('');
const rtel = ref('')
const rpassword = ref('')

// 手机号验证
const pattern = /^1[3-9]\d{9}$/;
// 密码验证
const validator = (val: any) => /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{6}$/.test(val);

interface LoginResponse {
    token?: string;
    message: string;
    username: string;
    id: string;
}

const loginSubmit = (values: any) => {
    userApi.login.call({
        tel: values.ltel,
        password: values.lpassword
    }).then((res) => {
        const data = res.data as LoginResponse | null; // 允许data为null

        if (!data || !data.token) {
            // 如果data为null或没有token，则显示错误信息
            showFailToast(res.message || '登录失败，未获取到有效数据');
        } else {
            sessionStorage.setItem("username", data.username);
            sessionStorage.setItem("tel",values.ltel)
            sessionStorage.setItem("userId", data.id);
            sessionStorage.setItem("token",data.token)
            // console.log("loginRes=>",data);
            router.push({ path: "/moveshow/mine" });
        }
    }).catch(() => {
        // 处理网络错误或API调用失败的情况
        showFailToast('登录失败，请稍后再试');
    });
};

const registerSubmit = (values: any) => {
    // console.log('registerSubmit=>', values);
    userApi.register.call({
        tel: values.rtel,
        password: values.rpassword
    }).then((res) => {
        // console.log("res=>", res);
        if (res.data == '注册成功') {
            userApi.login.call({
                tel: values.rtel,
                password: values.rpassword
            }).then((res) => {
                // console.log("res=>", res);
                const data = res.data as LoginResponse;
                if (data === null) {
                    showFailToast(res.message)
                } else {
                    sessionStorage.setItem("username", data.username);
                    router.push({ path: "/moveshow/mine" })
                }
            })
        } else {
            showFailToast(res.message)
        }

    })
};

const formShow = ref(true)
const switchForm = () => {
    formShow.value = !formShow.value
}
</script>

<style scoped>
:global(.van-nav-bar__title) {
    font-size: 18px;
    font-weight: 400;
}
</style>