<!-- @format -->

<template>
  <div>
    <b-card :header="stationinfo.data.stationName" class="text-center">
      <b-card-text>
        <v-simple-table>
          <template v-slot:default>
            <tbody>
              <tr>
                <td>주소</td>
                <td>{{ stationinfo.data.stationAddress }}</td>
              </tr>
              <tr>
                <td>거리</td>
                <td>{{ stationinfo.data.distance }}Km (도보)</td>
              </tr>
              <tr>
                <td>소요시간</td>
                <td>{{ stationinfo.data.walktime }}분 (도보)</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table></b-card-text
      >
    </b-card>
  </div>
</template>

<script>
export default {
  name: "SubwayItem",
  props: {
    stationinfo: Object,
  },
};
</script>

<style></style>
