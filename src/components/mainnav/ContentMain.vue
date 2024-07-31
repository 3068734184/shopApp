<template>
    <el-row class="row-bg" :gutter="10" justify="space-around">
        <el-col :span="12">
            <el-calendar v-model="value" />
        </el-col>
        <el-col :span="12">
            <div id="tp-weather-widget" class="weather"></div>
        </el-col>

    </el-row>

</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { weatherWidget } from '../../utils/weatherWidget';
const value = ref(new Date())

onMounted(() => {
    weatherWidget();
});
</script>
<style scoped>
.el-calendar {
    --el-calendar-cell-width: 72.5px !important;
}


:global(.ctoKFT) {
    position: unset !important;
}

:global(.jUtbhq) {
    position: unset !important;
}

:global(.WsCwJ) {
    position: unset !important;
}

:global(.hLyGaQ) {
    position: unset !important;
}

:global(.ihAeOA) {
    width: 650px !important;
    margin: 0 auto;
}

.weather {
    padding-top: 12px;
    height: 340px;
}
</style>