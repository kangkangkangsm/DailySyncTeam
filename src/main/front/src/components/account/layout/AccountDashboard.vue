<script setup>
import { ref } from 'vue'
import DateScore from '@/components/account/leftView/DateScore.vue'
import AccountItemList from '@/components/account/leftView/AccountItemList.vue'
import AccountPopup from '@/components/account/AccountPopup.vue'

defineProps({
  selectedDate: String,
})
const viewUpdatePopup = ref(false)
</script>
<template>
  <div>
    <RouterLink to="account">Account Page 이동 >></RouterLink>
    <DateScore :prop-date="selectedDate" />
    <button @click="viewUpdatePopup = true">+</button>
    <AccountItemList :prop-date="selectedDate" />
  </div>
  <AccountPopup
    v-if="viewUpdatePopup"
    mode="create"
    :account="{ accountDate: selectedDate }"
    @close="viewUpdatePopup = false"
  />
</template>
