<template>
  <div class="account-left">
    <div class="account-fixed">
      <div class="account-divide">
        <div class="first">
          <div class="tit">소득</div>
          <div>{{ numToWon(money.plus) }}</div>
        </div>
        <div class="second">
          <div class="tit">지출</div>
          <div>{{ numToWon(money.minus) }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { numToWon } from '@/lib/accountLib'
import { useDateStore } from '@/stores/dateStore'
import { useRefreshStore } from '@/stores/refreshStore'

export default {
  props: {
    propDate: String,
  },
  data() {
    const dateStore = useDateStore()
    const refreshStore = useRefreshStore()
    return {
      dateStore,
      refreshStore,
      money: { plus: 0, minus: 0 },
    }
  },
  watch: {
    propDate() {
      this.fetchData()
    },
    'dateStore.selectedDate': 'fetchData',
    'refreshStore.refreshState': 'fetchData',
  },
  methods: {
    numToWon,
    async fetchData() {
      const date = this.propDate ?? this.dateStore.selectedDate
      const url = `account/items/sum/${date}`
      const { data } = await this.$axios.get(url)
      this.money.plus = data.plusSumAmount
      this.money.minus = -data.minusSumAmount
    },
  },
  mounted() {
    this.fetchData()
  },
}
</script>

<style lang="scss">
.date-score-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 20px;
}

.date-score-container div {
  font-size: 16px;
  font-weight: 600;
}

.date-score-container div:nth-child(odd) {
  color: #333;
}

.date-score-container div:nth-child(even) {
  font-size: 18px;
  color: #2f8fe8;
}

.date-score-container .income,
.date-score-container .expense {
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  padding: 8px 12px;
  border-radius: 8px;
  width: 100px;
}

.date-score-container .income {
  background-color: #d1f7d7;
  color: #4caf50;
}

.date-score-container .expense {
  background-color: #ffebee;
  color: #e57373;
}

@media (max-width: 600px) {
  .date-score-container {
    flex-direction: column;
    align-items: flex-start;
    padding: 15px;
  }

  .date-score-container div {
    font-size: 14px;
  }

  .date-score-container .income,
  .date-score-container .expense {
    width: 100%;
    font-size: 18px;
    margin-top: 5px;
  }
}
</style>
