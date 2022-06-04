<!-- @format -->

<template>
  <div>
    <button
      v-if="apart"
      class="aptinfolist"
      id="aptinfolist"
      @click="moveapartmap"
    >
      <div class="apartinfolist-item p-3">
        <b-row class="mb-2">
          <b-col>
            <img :src="apartimg" style="width: 100%; height: 200px" /> </b-col
          ><b-col>
            <b-col>
              <h4 class="aptNamelist mt-7 mb-7">
                {{ apart.aptName }}
              </h4>
              <b-row>
                <b-col>
                  <router-link to="/apart/nearbysearch">
                    <!-- <b-button
                      variant="outline-success"
                      data-bs-toggle="modal"
                      data-bs-whatever="@mdo"
                      @click="searcharound"
                      class="mr-2"
                    >
                      주변상권
                    </b-button> -->

                    <v-btn
                      class="mx-2"
                      fab
                      dark
                      small
                      color="indigo"
                      @click="searcharound"
                    >
                      <v-icon dark> mdi-plus </v-icon>
                    </v-btn>
                  </router-link> </b-col
                ><b-col>
                  <!-- <b-icon
                    icon="star-fill"
                    color="orange"
                    font-scale="2"
                    v-if="loginUser"
                    @click="addinter"
                  ></b-icon>
                  <b-icon
                    v-else
                    @click="addcheck"
                    icon="star-fill"
                    color="grey"
                    font-scale="2"
                  ></b-icon> -->

                  <v-btn
                    class="mx-2"
                    fab
                    dark
                    small
                    v-if="loginUser"
                    @click="addinter"
                    color="pink"
                  >
                    <v-icon dark> mdi-heart </v-icon>
                  </v-btn>

                  <v-btn
                    class="mx-2"
                    fab
                    dark
                    small
                    v-else
                    @click="addcheck"
                    color="secondery"
                  >
                    <v-icon dark> mdi-heart </v-icon>
                  </v-btn>
                </b-col>
              </b-row>
            </b-col>
          </b-col>
        </b-row>

        <b-card>
          <template #header>
            <h6 class="mb-0">
              <b-icon
                icon="exclamation-circle-fill"
                variant="secondary"
              ></b-icon>
              아파트 상세 정보
            </h6>
          </template>
          <b-card-text>
            <v-simple-table>
              <template v-slot:default>
                <tbody>
                  <tr>
                    <td width="25%"><strong> 도로명 </strong></td>
                    <td>
                      {{ selSidoCode }} {{ selGugunCode }}
                      {{ selDongCode }}
                      {{ apart.roadName }}
                    </td>
                  </tr>
                  <tr>
                    <td><strong>지번</strong></td>
                    <td>
                      {{ selSidoCode }} {{ selGugunCode }}
                      {{ selDongCode }}
                      {{ apart.jibun }}
                    </td>
                  </tr>
                  <tr>
                    <td><strong>건축년도 </strong></td>
                    <td>{{ apart.buildYear }}년</td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </b-card-text>

          <v-row>
            <v-expansion-panels accordion>
              <v-expansion-panel>
                <v-expansion-panel-header>거래내역</v-expansion-panel-header>
                <v-expansion-panel-content>
                  <apart-order-vue @orderby="changeorder"></apart-order-vue>
                  <apart-detail-list
                    :apartdetail="apart"
                    :sortValue="sortValue"
                  ></apart-detail-list>
                </v-expansion-panel-content>
              </v-expansion-panel>
            </v-expansion-panels>
          </v-row>
        </b-card>
      </div>
    </button>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import ApartDetailList from "./ApartDetailList.vue";
import ApartOrderVue from "./ApartOrder.vue";
import http from "@/api/http.js";
export default {
  name: "ApartListItem",
  data() {
    return {
      isapartdetail: false,
      sortValue: "",
      apartimg: "",
    };
  },
  created() {
    this.get_apartimg();
  },
  components: {
    ApartDetailList,
    ApartOrderVue,
  },
  props: {
    apart: Object,
  },
  watch: {
    apart: function () {
      this.get_apartimg();
    },
  },
  methods: {
    ...mapMutations(["SET_TARGET", "SET_MAP_POINT"]),
    changeorder(sortValue) {
      this.sortValue = sortValue;
    },
    searcharound() {
      this.SET_TARGET(this.apart);
    },
    moveapartmap() {
      console.log(this.apart);
      this.SET_MAP_POINT({ lat: this.apart.lat, lng: this.apart.lng });
    },
    apartdetail() {
      this.isapartdetail = !this.isapartdetail;
    },
    get_apartimg() {
      console.log("아파트 이름" + this.apart.aptName);
      console.log(this.selSidoCode + this.selGugunCode + this.selDongCode);
      http
        .get(
          `/crawling/${this.selSidoCode}/${this.selGugunCode}/${this.selDongCode}/${this.apart.aptName}`
        )
        .then(({ data }) => {
          console.log(data);
          this.apartimg = data;
        });
    },
    addcheck() {
      if (!this.loginUser) {
        alert("로그인이 필요한 서비스입니다");
        this.$router.push({ name: "user" });
        return;
      }
    },
    addinter() {
      if (confirm("즐겨찾기에 추가하시겠습니까?")) {
        http
          .post("/userinter", {
            id: this.loginUser.id,
            selsidocode: this.selSidoCode,
            selguguncode: this.selGugunCode,
            seldongcode: this.selDongCode,
            aptName: this.apart.aptName,
            img: this.apartimg,
            roadname:
              this.selSidoCode +
              " " +
              this.selGugunCode +
              " " +
              this.selDongCode +
              " " +
              this.apart.roadName,
            jibun:
              this.selSidoCode +
              " " +
              this.selGugunCode +
              " " +
              this.selDongCode +
              " " +
              this.apart.jibun,
            buildyear: this.apart.buildYear,
          })
          .then((data) => {
            let msg = "추가 도중 문제가 발생했습니다.";
            if (data.data == "success") {
              console.log(data.data);
              msg = "즐겨찾기에 추가되었습니다.";
            } else {
              console.log(data);
              msg = "이미 추가된 항목입니다.";
            }
            alert(msg);
          });
      }
    },
  },
  computed: {
    ...mapState([
      "aparts",
      "selSidoCode",
      "selGugunCode",
      "selDongCode",
      "loginUser",
    ]),
  },
};
</script>

<style>
.aptinfolist {
  width: 100%;
}
</style>
