<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide>
        <template #img>
          <img
            width="1024"
            height="480"
            class="d-block w-100"
            src="@/assets/images/banner3.jpg"
            alt="image slot"
          />
        </template>
        <p style="font-size: 20px">Welcome, Happy House</p>
      </b-carousel-slide>

      <b-carousel-slide>
        <template #img>
          <img
            class="d-block w-100"
            width="1024"
            height="480"
            src="@/assets/images/banner2.jpg"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
      <b-carousel-slide caption="김은경 성문조" text="spring 관통 프로젝트">
        <template #img>
          <img
            width="1024"
            height="480"
            class="d-block w-100"
            src="@/assets/images/banner1.png"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
    </b-carousel>

    <p class="mt-4">
      Slide #: {{ slide }}<br />
      Sliding: {{ sliding }}
    </p>
  </div>
</template>

<script>
export default {
  name: "BannerView",
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    onSlideStart(slide) {
      console.log(slide);
      this.sliding = true;
    },
    onSlideEnd(slide) {
      console.log(slide);
      this.sliding = false;
    },
  },
};
</script>

<style></style>
