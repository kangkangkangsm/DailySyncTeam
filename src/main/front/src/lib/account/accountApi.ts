import axios from '@/plugins/axios'
import type { Account } from '@/types'

//조회
export const getAccountList = async (year: number, month: number): Promise<Array<Account>> =>
  (await axios.get(`account/items/${year}/${month}`)).data
//입력
export const saveAccountItem = async (account: Account): Promise<Account> =>
  (await axios.post('account/items', account)).data
//수정
export const updateAccountItem = async (id: number, account: Account): Promise<boolean> => {
  return (await axios.put(`account/items/${id}`, account)).data
}
//삭제
export const deleteAccountItem = async (id: number): Promise<boolean> => {
  return (await axios.delete(`account/items/${id}`)).data
}
