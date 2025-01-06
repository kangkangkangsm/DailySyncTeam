<script setup lang="ts">
import { ref } from 'vue'
import { categories } from '@/constants/accountCategory'
import { formatCurrencyKRW } from '@/lib/account/accountLib'
import { useAccountStore } from '@/stores/account/accountStore'
import type { Account } from '@/types'
import AccountPopup from '@/components/account/AccountPopup.vue'

const props = defineProps<{
  account?: Account
}>()
const viewUpdatePopup = ref<boolean>(false)
const accountStore = useAccountStore()

const deleteItem = async () => {
  if (props.account && confirm('삭제하시겠습니까?')) {
    await accountStore.deleteItem(props.account.id)
  }
}
</script>
<template>
  <div class="list-item">
    <div class="tit-box">
      <div :style="{ color: categories[account.category].color }" class="title">
        {{ categories[account.category].name }}
      </div>
    </div>
    <div class="tit">{{ account.title }}</div>
    <div class="desc">{{ account.description }}</div>
    <div class="amount" :class="account.amount < 0 ? 'minus' : 'plus'">
      {{ formatCurrencyKRW(account.amount) }}
    </div>
    <div class="btn-box">
      <button @click="viewUpdatePopup = true" class="edit-btn"></button>
      <button @click="deleteItem" class="remove-btn"></button>
    </div>
  </div>
  <AccountPopup
    v-if="viewUpdatePopup"
    :account="props.account"
    mode="update"
    @close="viewUpdatePopup = false"
  />
</template>
