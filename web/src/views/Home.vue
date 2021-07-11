<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          :style="{ height: '100%', borderRight: 0 }"
          @click="handleClick"
          :openKeys="openKeys"
      >
        <a-menu-item  @click="handleQueryEbook">
          <MailOutlined />
          <span>欢迎</span>
        </a-menu-item>
        <a-sub-menu v-for="item in level1" :key="item.id" >
          <template v-slot:title>
            <span><user-outlined />{{item.name}}</span>
          </template>
          <a-menu-item v-for="child in item.children" :key="child.id">
            <MailOutlined /><span>{{child.name}}</span>
          </a-menu-item>
        </a-sub-menu>
        <a-menu-item key="tip" :disabled="true">
          <span>以上菜单在分类管理配置</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-list item-layout="vertical" size="large" :pagination="pagination" :data-source="ebooks" :grid="{ gutter: 16, column: 4 }" >
        <template #renderItem="{ item }">
          <a-list-item key="item.name">
            <template #actions>
              <span>
                <component v-bind:is="'FileTextOutlined'"  />
                {{ item.docCount }}
              </span>
              <span>
                <component v-bind:is="'EyeOutlined'"  />
                {{ item.viewCount }}
              </span>
              <span>
                <component v-bind:is="'LikeOutlined'"  />
                {{ item.voteCount }}
              </span>
            </template>
            <a-list-item-meta :description="item.description">
              <template #title>
                <router-link :to="'/doc?ebookId=' + item.id">
                  {{ item.name }}
                </router-link>
              </template>
              <template #avatar><a-avatar :src="item.avatar" />
              </template>
            </a-list-item-meta>
            {{ item.content }}
          </a-list-item>
        </template>
      </a-list>
    </a-layout-content>
  </a-layout>
</template>
<script lang="ts">
import {defineComponent, onMounted, ref} from "vue";
import axios from "axios";
import {EyeOutlined, FileTextOutlined, LikeOutlined} from '@ant-design/icons-vue';
import {message} from 'ant-design-vue';
import {Tool} from "@/util/tool";

const listData: Record<string, string>[] = [];

export default defineComponent({
  name:'Home',
  components: {
    LikeOutlined,
    EyeOutlined,
    FileTextOutlined,
  },
  setup(){

    const ebooks = ref();
    const level1 = ref();
    const openKeys =  ref();
    let categorys: any;
    /**
     * 查询所有分类
     **/
    const handleQueryCategory = () => {
      axios.get("/category/all").then((response) => {
        const data = response.data;
        if (data.success) {
          categorys = data.content;
          console.log("原始数组：", categorys);

          level1.value = [];
          level1.value = Tool.array2Tree(categorys, 0);
          console.log("树形结构：", level1.value);
        } else {
          message.error(data.message);
        }
      });
    };

    axios.get("/ebook/search",{
      params:{
        page:1,
        size:1000,
      }
    }).then((response) => {
      const data = response.data;
      ebooks.value = data.content.list;
    });

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 20,
    };

    /**
     * 查询电子书
     */
    const handleQueryEbook = () => {
      axios.get("/ebook/search", {
        params: {
          page: 1,
          size: 1000,
          category2Id: category2Id
        }
      }).then((response) => {
        const data = response.data;
        ebooks.value = data.content.list;
      });
    };

    /**
     * 点击菜单查电子书
     */
    let category2Id = 0;
    const handleClick = (value: any) => {
        category2Id = value.key;
        handleQueryEbook();
    };



    onMounted(() => {
      handleQueryCategory();
      handleQueryEbook();


    });

    return {
      ebooks,
      listData,
      pagination,
      level1,
      openKeys,
      handleClick,
      handleQueryEbook,

      handleQueryCategory,
    }
  },
})
</script>

<style scoped>
#components-layout-demo-top-side-2 .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 28px 16px 0;
  float: left;
}
  .ant-avatar {
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 8%;
    margin: 5px 0;
  }
</style>
