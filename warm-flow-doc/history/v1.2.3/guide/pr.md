# 贡献者列表

<div class="project-contributors__container">
  <h3 class="header">贡献者列表 ({{authorsList.length}})</h3>
  <div class="grid">
    <div class="user-list-item" v-for="(item, index) in authorsList" :key="index">
      <span class="flex-shrink-0"><img class="avatar" src="/logo.png"></span>
      <div class="content">
        <span class="username">{{ item.name }}</span>
        <span class="sub-info">Commits: {{ item.contributions }}</span>
      </div>
    </div>
  </div>
</div>

<script>
import { ref, onMounted } from 'vue';
 
export default {
  setup() {
    const authorsList = ref([]);
 
    const fetchData = async () => {
      try {
        const response = await fetch('https://gitee.com/api/v5/repos/dromara/warm-flow/contributors?type=authors');
        authorsList.value = await response.json();
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
 
    onMounted(fetchData);
 
    return {
      authorsList,
    };
  },
};
</script>

<style>
.header {
  margin: calc(2rem - 0.165em) 0em 1rem;
}
.user-list-item {
  display: inline-flex;
  align-items: center;
  margin: 15px 0;
  padding: 14px 0 14px 10px;
  width: 178px;
  .avatar {
    height: 50px;
    margin-right: 10px;
    border-radius: 50%;
  }
  .content {
    line-height: 22px;
    .username,
    .sub-info {
      display: block;
      color: #40485b;
      font-size: 14px;
      font-weight: 400;
    }
    .username {
      font-weight: 600;
      font-size: 16px;
    }
  }
}
</style>

