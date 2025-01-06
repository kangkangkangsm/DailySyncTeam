<script setup lang="ts">
import { computed, ref, watch } from 'vue'
import { useDateStore } from '@/stores/account/dateStore'
import { useAccountStore } from '@/stores/account/accountStore'
import AccountItem from '@/components/account/leftView/AccountItem.vue'
import type { Account } from '@/types'

const props = defineProps({
  propDate: {
    type: String,
    default: '',
  },
})

const dateStore = useDateStore()
const accountStore = useAccountStore()

const plusList = ref<Account[]>([])
const minusList = ref<Account[]>([])

const dateToUse = computed(() => props.propDate || dateStore.selectedDate)
const init = async () => {
  if (
    props.propDate &&
    (accountStore.initMonth === null ||
      parseInt(props.propDate.split('-')[0]) != accountStore.initYear ||
      parseInt(props.propDate.split('-')[1]) != accountStore.initMonth)
  ) {
    console.log('dd')
    await accountStore.initAccountList(
      parseInt(props.propDate.split('-')[0]),
      parseInt(props.propDate.split('-')[1]),
    )
  }
}
const fetchDataList = () => {
  const date = dateToUse.value
  const list = accountStore.dateList(date)
  const plusListData: Account[] = []
  const minusListData: Account[] = []
  for (let account of list) {
    if (account.amount > 0) {
      plusListData.push(account)
    } else {
      minusListData.push(account)
    }
  }
  plusList.value = plusListData
  minusList.value = minusListData
}
watch(
  dateToUse,
  () => {
    init()
    fetchDataList()
  },
  { immediate: true },
)
watch(() => accountStore.accountList, fetchDataList)
</script>
<template>
  <div class="account-list-wrap">
    <div class="item top">
      <div class="tit">지출 내역</div>
      <div class="list">
        <template v-if="minusList.length > 0">
          <AccountItem v-for="(account, index) in minusList" :key="index" :account="account" />
        </template>
        <div v-else class="none"><span>내역이 없습니다.</span></div>
      </div>
    </div>
    <div class="item bottom">
      <div class="tit">소득 내역</div>
      <template v-if="plusList.length > 0">
        <AccountItem v-for="(account, index) in plusList" :key="index" :account="account" />
      </template>
      <div v-else class="none"><span>내역이 없습니다.</span></div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.account {
  &-list-wrap {
    display: flex;
    flex-direction: column;
    gap: 10px 0;
    flex-grow: 1;
    width: 100%;
    .item {
      height: 50%;
      .tit {
        font-weight: bold;
        margin-bottom: 10px;
        line-height: 1;
      }
      .list {
        height: calc(100% - 26px);
        overflow-y: auto;
        padding: 10px;
      }
      .none {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
        span {
          padding-top: 60px;
          background: url('@/assets/images/no_list_icon.png') center top / 50px no-repeat;
          font-weight: bold;
          color: var(--color-contrastyA);
        }
      }
    }
  }
}
</style>
