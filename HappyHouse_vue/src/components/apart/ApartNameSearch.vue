<template>
  <div>
    <input type="hidden" name="pg" value="1" />
    <select class="col border m-2" style="height: 40px" name="key" id="key">
      <option value="aptName">아파트이름</option>
    </select>

    <input type="text" class="col ml-1 form-control" name="word" id="word" />
    <button type="button" class="col btn btn-outline-primary" id="word-btn">
      검색
    </button>
  </div>
</template>

<script>
export default {};
</script>

<style></style>
