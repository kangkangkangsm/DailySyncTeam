import { defineStore } from 'pinia'
import type { Account } from '@/types'
import {
  deleteAccountItem,
  getAccountList,
  saveAccountItem,
  updateAccountItem,
} from '@/lib/account/accountApi'
import { formatCurrencyKRW } from '@/lib/account/accountLib'
interface GroupedData {
  [key: string]: {
    plusSum: number
    minusSum: number
  }
}
interface GroupedItem {
  start: string
  title: string
  color: string
}
interface AccountState {
  accountList: Account[]
  initYear?: number
  initMonth?: number
}

const groupByDate = (accountList: Account[]): GroupedData => {
  return accountList.reduce((acc, { accountDate, amount }) => {
    if (!acc[accountDate]) {
      acc[accountDate] = { plusSum: 0, minusSum: 0 }
    }
    if (amount > 0) {
      acc[accountDate].plusSum += amount
    } else {
      acc[accountDate].minusSum += amount
    }
    return acc
  }, {} as GroupedData)
}

const formatGroupedData = (groupedData: GroupedData): GroupedItem[] => {
  return Object.entries(groupedData).flatMap(([date, { plusSum, minusSum }]) => {
    const result: GroupedItem[] = []
    if (plusSum > 0)
      result.push({ start: date, title: formatCurrencyKRW(plusSum), color: '#ff334b' })
    if (minusSum < 0)
      result.push({ start: date, title: formatCurrencyKRW(minusSum), color: '#4f7eff' })
    return result
  })
}
const formatSumData = (list: Account[]): { plus: number; minus: number } => {
  return list.reduce(
    (acc, { amount }) => {
      if (amount > 0) {
        acc.plus += amount
      } else {
        acc.minus += amount
      }
      return acc
    },
    { plus: 0, minus: 0 },
  )
}
export const useAccountStore = defineStore('accountStore', {
  state: (): AccountState => ({
    accountList: [],
    initYear: undefined,
    initMonth: undefined,
  }),

  getters: {
    calendarList: (state): GroupedItem[] => {
      const grouped = groupByDate(state.accountList)
      return formatGroupedData(grouped)
    },
    monthSum: (state): { plus: number; minus: number } => {
      return formatSumData(state.accountList)
    },
    monthFixedSum: (state): { plus: number; minus: number } => {
      return formatSumData(state.accountList.filter(({ fixed }) => fixed))
    },
    fixedList: (state): Account[] => {
      return state.accountList.filter(({ fixed }) => fixed)
    },
  },

  actions: {
    dateList(date: string): Account[] {
      return this.accountList.filter((acc) => acc.accountDate === date)
    },
    setAccountList(list: Account[]): void {
      this.accountList = [...list]
    },
    async initAccountList(year: number, month: number) {
      if (this.initYear === year && this.initMonth === month) {
        return
      }
      const list = await getAccountList(year, month)
      this.setAccountList(list)
      this.initYear = year
      this.initMonth = month
    },
    async saveItem(account: Account) {
      const savedAccount: Account = await saveAccountItem(account)
      this.setAccountList([...this.accountList, savedAccount])
    },
    async updateItem(id: number, account: Account) {
      const updatedAccount = await updateAccountItem(id, account)
      const updatedList = this.accountList.map((item) => (item.id === id ? updatedAccount : item))
      this.setAccountList(updatedList)
    },
    async deleteItem(id: number) {
      const status = await deleteAccountItem(id)
      if (status) {
        this.setAccountList(this.accountList.filter((account: Account) => account.id != id))
      }
    },
  },
})
