<template>

    <!-- <el-button type="primary" style="margin-left: 16px" @click="drawer = true">
        open
      </el-button> -->
    <el-avatar shape="square" :size="100" :src="props.modelValue" @click="drawer = true">
        <img src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png" alt="">
    </el-avatar>
    <el-drawer v-model="drawer" title="我的图库" direction="ttb" size="50%">
        <el-upload v-model:file-list="fileList" list-type="picture-card" :auto-upload="false" :on-Change="onChange">
            <el-icon>
                <Plus />
            </el-icon>

            <template #file="{ file }">
                <div>
                    <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                    <span class="el-upload-list__item-actions">
                        <span class="el-upload-list__item-preview" @click="getImgurl(file.url)">
                            <el-icon><zoom-in /></el-icon>
                        </span>
                    </span>
                </div>
            </template>
        </el-upload>
    </el-drawer>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { uploadApi } from '../api'

const drawer = ref(false)
// const imgurl = ref()


const fileList = ref<any>([
    {
        name: 'food.jpeg',
        url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100',
    },])

const getImgurl = (imageUrl: any) => {
    // console.log("图片地址===",imageUrl);
    // imgurl.value = imageUrl //赋值头像
    emit('update:modelValue', imageUrl) //父组件监听
    drawer.value = false //关闭弹框
}


const props = defineProps({
    modelValue: {
        type: String,
        default: ''
    }
})

//告知父类我更改了什么内容
const emit = defineEmits(['update:modelValue'])
const onChange = (uploadFile: any, uploadFiles: any) => {
    console.log("uploadFile===", uploadFile, "uploadFiles===", uploadFiles);
    let name = uploadFile.name

    let file = uploadFile.raw
    let reader = new FileReader();

    reader.readAsDataURL(file)
    reader.onload = () => {
        console.log(reader.result);
        CallUploadApi(name, reader.result)

    }

}

onMounted(() => {
    uploadImgList()
})

const CallUploadApi = (name: any, base64: any) => {
    uploadApi.upload.call({ name: name, base64: base64 }).then((res: any) => {
        console.log(res);
        uploadImgList()
    })
}

//图片展示
const uploadImgList = () => {
    uploadApi.imgList.call().then((res: any) => {
        console.log(res);
        fileList.value = res
    })
}



</script>
<style scoped></style>