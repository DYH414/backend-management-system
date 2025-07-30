<template>
  <div>
    <div>
      <el-button @click="()=>{router.push('/Manager/Home')}">跳转</el-button>
    </div>
    <div style="font-size:20px;color:darkseagreen;font-weight: bold">
      hello
    </div>
    <div style="font-size:20px;color:darkseagreen;font-weight: bold">
      {{ a }}
      {{ b }}
    </div>
    <div style="font-size:20px;color:darkseagreen;font-weight: bold">
      {{ data.a }} {{ data.b }} {{ data.c }}<br>
    </div>
    <div>
      <input v-model="data.a" type="text"/>
    </div>
    <div>
      <span v-if="data.name === '大爷' ">大爷</span>
      <span v-if="data.name === '小爷'">小爷</span>
      <span v-else>中爷</span>
    </div>
    <div style="display:flex">
      <div @click="show(item)"
           style="width:300px;height: 300px;background-color: darkseagreen;margin-right: 10px;text-align: center;line-height: 300px;font-size: 30px"
           v-for="item in data.arr">{{ item }}
      </div>
    </div>
    <div>
      <select style="font-size: large">
        <option v-for="item in data.option">{{ item }}</option>
      </select>
    </div>
    <div style="margin: 10px">
      <button @click="click">点我</button>
    </div>
    <div :style="data.box"></div>
    <div>
      <img :src="data.img" alt="">
    </div>
    <div style="margin:20px;">
      <el-row style="margin: 10px">
        <el-button @click="clickNew" :style="{color:'red' }" plain>默认按钮</el-button>
        <el-button type="primary" loading color="#189">主要按钮</el-button>
        <el-button type="success">成功按钮</el-button>
        <el-button type="info">信息按钮</el-button>
        <el-button type="warning">警告按钮</el-button>
        <el-button type="danger">危险按钮</el-button>
      </el-row>

      <el-row style="margin: 10px">
        <el-button plain>朴素按钮</el-button>
        <el-button type="primary" plain>主要按钮</el-button>
        <el-button type="success" plain>成功按钮</el-button>
        <el-button type="info" plain>信息按钮</el-button>
        <el-button type="warning" plain>警告按钮</el-button>
        <el-button type="danger" plain>危险按钮</el-button>
      </el-row>

      <el-row style="margin: 10px">
        <el-button round>圆角按钮</el-button>
        <el-button type="primary" round>
          <el-icon>
            <Loading/>
          </el-icon>
          加载中
        </el-button>
        <el-button type="success" loading>成功按钮</el-button>
        <el-button type="info" round>信息按钮</el-button>
        <el-button type="warning" round>警告按钮</el-button>
        <el-button type="danger" round>危险按钮</el-button>
      </el-row>

      <el-row style="margin: 10px">
        <el-button icon="el-icon-search" circle></el-button>
        <el-button type="primary" icon="el-icon-edit" circle></el-button>
        <el-button type="success" icon="el-icon-check" circle></el-button>
        <el-button type="info" icon="el-icon-message" circle></el-button>
        <el-button type="warning" icon="el-icon-star-off" circle></el-button>
        <el-button type="danger" circle>
          <el-icon>
            <Edit/>
          </el-icon>
        </el-button>
      </el-row>
    </div>
    <div style="margin:20px;">
      <span>
         <el-icon>
        <Edit/>
      </el-icon>
      </span>

      <span style="margin:20px;">
        <el-icon style="top:3px; right: 3px"><View/></el-icon>100
      </span>

    </div>

  </div>
</template>

<script setup>
import {reactive, ref} from "vue";
import {Edit, Loading, View} from "@element-plus/icons-vue";
import router from "@/router/index.js";

const a = ref(1)
const b = ref("hello vue")
const data = reactive({
  id: router.currentRoute.value.query.id,
  name: router.currentRoute.value.query.name,
  a: 123,
  b: "fake",
  c: false,
  arr: [1, 2, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9],
  option: ["大爷", "小爷", "中爷"],
  box: {
    width: "102px",
    height: "102px",
    backgroundColor: "red",
  },
  img: 'https://picsum.photos/200/200?random=1'
})
const show = (value) => {
  alert(value);
}
const clickNew = () => {
  alert("点击成功");
}
const click = () => {
  alert("好运+1");
}
console.log("当前id为" + data.id)
console.log("当前name为" + data.name)
</script>