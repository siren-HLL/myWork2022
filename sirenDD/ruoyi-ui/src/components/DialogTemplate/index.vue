<!-- dialog模板 -->
<template>
  <div class="app-container">
    <el-dialog
      :width="width"
      :title="title"
      :visible.sync="diaVisible"
      v-dialogDrag
      v-if="diaVisible"
      :before-close="close"
      :top="top"
    >
      <slot name="dia_Content">

      </slot>

      <div
        slot="footer"
        class="dialog-footer"
        v-if="noFooter ? false : true"
      >
        <el-button
          size="mini"
          @click="cancel"
        > 取 消
        </el-button>
        <el-button
          type="primary"
          size="mini"
          @click="save"
        > 确 定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "diaTemplate",
  props: {
    diaVisible: {
      //dialog的显示
      type: Boolean,
      require: true,
      default: false,
    },
    title: {
      //标题
      type: String,
      require: false,
      default: "设备信息",
    },
    width: {
      type: String,
      require: false,
      default: "1200px",
    },
    noFooter: {
      //底部确认栏
      type: Boolean,
      require: false,
      default: false,
    },
    top: {
      type: String,
      require: false,
      default: "15vh",
    },
  },
  data() {
    return {};
  },
  mounted() {},
  computed: {},
  methods: {
    //dialog方法
    close() {
      this.$emit("close");
    },
    cancel() {
      this.$emit("cancel");
    },
    save() {
      this.$emit("save");
    },
  },
};
</script>
<style scoped>
</style>
