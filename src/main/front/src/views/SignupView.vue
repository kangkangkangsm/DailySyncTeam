<template>
  <div class="user-wrap">
    <div class="user-container">
      <div class="ip-list">
        <div class="tit-box">
          <p class="tit"><label for="email">이메일</label></p>
        </div>
        <div class="bot-box">
          <div class="ip-box">
            <input type="email" id="email" v-model="email" placeholder="이메일을 입력하세요" />
          </div>
        </div>
      </div>
      <div class="ip-list">
        <div class="tit-box">
          <p class="tit"><label for="userName">이름</label></p>
        </div>
        <div class="bot-box">
          <div class="ip-box">
            <input type="text" id="userName" v-model="userName" placeholder="이름을 입력하세요" />
          </div>
        </div>
      </div>
      <div class="ip-list">
        <div class="tit-box">
          <p class="tit"><label for="password">비밀번호</label></p>
        </div>
        <div class="bot-box">
          <div class="ip-box">
            <input
              type="password"
              id="password"
              v-model="password"
              placeholder="비밀번호를 입력하세요"
            />
          </div>
        </div>
      </div>
      <div class="ip-list">
        <div class="tit-box">
          <p class="tit">성별</p>
        </div>
        <div class="bot-box">
          <div class="ip-ra-txt">
            <input type="radio" v-model="gender" value="M" id="M" />
            <label for="M"> 남자 </label>
            <input type="radio" v-model="gender" value="F" id="F" />
            <label for="F"> 여자 </label>
          </div>
        </div>
      </div>

      <button @click="signUp" class="btn-default">가입하기</button>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  setup() {
    const router = useRouter()
    return { router }
  },
  data() {
    return {
      userName: '',
      password: '',
      gender: '',
      email: '',
    }
  },
  methods: {
    async signUp() {
      const url = 'signup'
      const requestBody = {
        userName: this.userName,
        password: this.password,
        gender: this.gender,
        email: this.email,
      }
      const { data } = await this.$axios.post(url, requestBody)
      if (data) {
        alert('가입 완료.')
        this.userName = ''
        this.password = ''
        this.gender = ''
        this.email = ''
        this.router.push('/login')
      }
    },
  },
  mounted() {
    localStorage.removeItem('jwtToken')
    sessionStorage.removeItem('jwtToken')
  },
}
</script>

<style lang="scss" scoped></style>
