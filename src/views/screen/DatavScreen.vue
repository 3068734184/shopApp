<template>
    <!--简易版本的大屏-->

    <el-row>
        <el-col :span="12">
            <!---饼状图-->
            <div id="pieChart" style="width:100%; height:350px"></div>
        </el-col>
        <el-col :span="12">
            <div id="pie2Chart" style="width: 100%; height: 350px;"></div>
        </el-col>
    </el-row>
    <el-row>
        <el-col :span="12">
            <!-- 折柱图 -->
            <div id="hisAndPoly" style="width: 100%; height: 340px;"></div>
        </el-col>
    </el-row>
</template>
<script setup lang="ts">
import * as echarts from 'echarts';
import { onMounted } from 'vue';
import { categoryProApi } from '../../api'

onMounted(() => {
    CallCategoryProApi();
})

const initPieCart = (data: any) => {
    var chartDom = document.getElementById('pieChart');
    var myChart = echarts.init(chartDom);
    var option: any;
    option = {
        title: {
            text: '商品类别统计图',
            subtext: '实时统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left'
        },
        series: [
            {
                name: 'CatePro Count',
                type: 'pie',
                radius: '50%',
                data: data,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    option && myChart.setOption(option);

}


const initHisAndPoly = (xData: any, yData: any) => {
    var chartDom = document.getElementById('hisAndPoly');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: { show: true, readOnly: false },
                magicType: { show: true, type: ['line', 'bar'] },
                restore: { show: true },
                saveAsImage: { show: true }
            }
        },
        legend: {
            data: ['类目下的商品数量']
        },
        xAxis: [
            {
                type: 'category',
                data: xData,
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: 'Count',
                min: 0,
                max: 10,
                interval: 1,
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ],
        series: [
            {
                name: '类目下的商品数量',
                type: 'bar',
                tooltip: {
                    valueFormatter: function (value: any) {
                        return value;
                    }
                },
                data: yData
            }
        ]
    };

    option && myChart.setOption(option);
}

const initPie2Chart = (data:any) => {
    var chartDom = document.getElementById('pie2Chart');
    var myChart = echarts.init(chartDom);
    var option;

    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: 'CatePro Count',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                padAngle: 5,
                itemStyle: {
                    borderRadius: 10
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 40,
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: data
            }
        ]
    };

    option && myChart.setOption(option);
}

const CallCategoryProApi = () => {
    categoryProApi.selectCategory.call().then((res: any) => {
        const xData = res.map((item: any) => item.name);
        const yData = res.map((item: any) => item.value);

        initPieCart(res);

        initHisAndPoly(xData, yData);

        initPie2Chart(res)
    })
}
</script>
<style scoped></style>