<template>
  <div class="auction-details">
    <h3 class="title">Expires:</h3>
    <span class="time-left" :class="{ urgent: isUrgent }">{{ timeLeft }}</span>
  </div>
</template>

<script>
import { Vue, Component, Prop } from "vue-property-decorator";

@Component
export default class AuctionTimer extends Vue {
  @Prop({type: Number, required: true})
  endDate
  timer = null;
  countdown = 0;

  get timeLeft() {
    if (this.countdown <= 0) {
      return "Expired";
    }
    return this.getReadableCountdown(this.countdown);
  }

  get isUrgent() {
    return this.countdown < 60000 && this.countdown > 0;
  }

  getReadableCountdown(countdown) {
    const timeObject = this.getTimeObject(countdown);
    if(timeObject.weeks > 0) {
      return new Date(this.endDate).toDateString();
    } else if (timeObject.days > 0) {
      return `${timeObject.days} days`;
    } else if (timeObject.hours > 1) {
      return `${timeObject.hours} h ${timeObject.minutes} min`;
    } else if (timeObject.hours > 0) {
      return `${timeObject.hours} h ${timeObject.minutes} min ${timeObject.seconds} sec`;
    } else if (timeObject.minutes > 0) {
      return `${timeObject.minutes} min ${timeObject.seconds} sec`;
    } else {
      return `${timeObject.seconds} sec`;
    }
  }

  getTimeObject(countdown) {
    const secondInMs = 1000;
    const minuteInMs = secondInMs * 60;
    const hourInMs = minuteInMs * 60;
    const dayInMs = hourInMs * 24;
    const weekInMs = dayInMs * 7;
    const timeObject = {}
    timeObject.weeks = Math.floor(countdown / weekInMs)
    countdown -= timeObject.weeks * weekInMs;
    timeObject.days = Math.floor(countdown / dayInMs)
    countdown -= timeObject.days * dayInMs;
    timeObject.hours = Math.floor(countdown / hourInMs)
    countdown -= timeObject.hours * hourInMs;
    timeObject.minutes = Math.floor(countdown / minuteInMs)
    countdown -= timeObject.minutes * minuteInMs;
    timeObject.seconds = Math.floor(countdown / secondInMs)
    return timeObject;
  }

  created() {
    this.countdown = this.endDate - Date.now();
    this.timer = setInterval(() => {
      this.countdown -= 1000
    }, 1000)
  }

  destroyed() {
    clearInterval(this.timer);
  }
}
</script>

<style lang="scss" scoped>
  .auction-details {
    display: flex;
    flex-direction: column;
    align-items: center;
    .title {
      color: #288781;
      font-style: italic;
      font-size: 18px;
    }
    .time-left {
      font-size: 18px;
    }
  }
  .urgent {
    font-weight: bold;
    color: red;
  }
</style>