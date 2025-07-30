<template>
  <div>
    <div>
      <h1>
        <router-link to="/Test">test</router-link>
      </h1>
      |<a href="/Test">home</a>
    </div>
    <div>
      <el-button @click="()=>{router.replace('/Manager/Test')}">test</el-button>
      <el-button @click="()=>{router.push({path:'/Manager/Test',query:{id:1,name:'张三'}})}">路由传参id=1</el-button>
    </div>


    <div>
      <el-input v-model="data.input"
                style="width: 240px;margin: 10px"
                clearable
                placeholder="Please input"/>
      {{ data.input }}
      <div>
        <el-input type="textarea" v-model:="data.decr"
                  style="width: 240px;margin: 10px"
                  clearable
                  placeholder="Please i"
                  suffix-icon="Search"/>
      </div>
      <div>
        <el-select v-model="data.value" placeholder="Select" style="width: 240px">
          <el-option
              v-for="item in data.option"
              :key="item"
              :label="item"
              :value="item"
          />
        </el-select>
        {{ data.value }}

        <div>
          <el-radio-group v-model="data.sex">
            <el-radio value="男">男</el-radio>
            <el-radio value="女">女</el-radio>
          </el-radio-group>
        </div>
        <div>
          <div>
            <el-radio-group v-model="data.tag" size="large" fill="#6cf">
              <el-radio-button label="1">1</el-radio-button>
              <el-radio-button label="2">2</el-radio-button>
              <el-radio-button label="3">3</el-radio-button>
              <el-radio-button label="4">Chicago</el-radio-button>
            </el-radio-group>
            {{ data.tag }}
          </div>
          <div>
            <el-checkbox v-model="data.checkbox" v-for="item in data.checklist" :label="item.name" size="large"/>
            {{ data.checkbox }}
          </div>
        </div>
        <div>
          <img src="@/assets/logo.svg" width="100px">
          <el-image :src="img" style="width: 100px" :preview-src-list="[img,'https://images.unsplash.com/photo-1506744038136-46273834b3fb?auto=format&fit=crop&w=800&q=80\n'+
'\n']"/>
        </div>

        <div>
          <el-carousel direction="vertical" height="300px" style="width: 500px; margin-left: 500px" motion-blur>
            <el-carousel-item v-for="item in data.imgs">
              <div class="carousel-content">
                <img :src="item" style="width: 500px">
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div>
          <el-date-picker v-model="data.time"
                          placeholder="Select time"
                          type="datetime"
                          style="width: 240px;margin: 10px"
                          format="YYYY-MM-DD HH:mm"
                          value-format="YYYY-MM-DD HH:mm"
                          clearable/>
        </div>
        {{ data.time }}

      </div>
      <div>
        <el-table :data="data.tableData" width="500px" stripe>
          <el-table-column prop="date" label="日期"/>
          <el-table-column prop="name" label="姓名"/>
          <el-table-column prop="address" label="地址"/>
        </el-table>
      </div>


    </div>

  </div>
</template>

<script setup>
import {Search} from "@element-plus/icons-vue";
import {reactive} from "vue";
import img from '@/assets/logo.svg'
import img1 from '@/assets/1.png'
import img2 from '@/assets/2.png'
import router from "@/router/index.js";

const data = reactive({
  input: null,
  decr: "Please inputeaeaeaeaeaeaeaeaeaeaeaea12112121212",
  option: ["小节", "中华健儿", "大爷"],
  value: null,
  sex: null,
  tag: 3,
  checklist: [{id: 1, name: "apple"}, {id: 2, name: "apple2"}],
  checkbox: [],
  url: "@/assets/logo.svg",
  imgs: [img1, img2],
  time: null,
  tableData: [
    {
      date: '2016-05-02',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1518 弄'
    },
    {
      date: '2016-05-04',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1517 弄'
    },
    {
      date: '2016-05-01',
      name: '王小虎',
      address: '上海市普陀区金沙江路 1519 弄'
    }
  ],
  id: router.currentRoute.value.query.id
})
console.log("当前id为" + data.id)
</script>

<style scoped>
.carousel-content {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100%; /* 确保高度占满整个轮播项 */
}
</style>
