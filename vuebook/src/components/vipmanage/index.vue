<template>
  <div>
    <Card>
      <div style="height: 50px;">
        <Form :model="seach" inline :label-width="40">
          <FormItem label="姓名" prop="name">
            <Input type="text" v-model="seach.name" placeholder="请输入姓名"></Input>
          </FormItem>
          <FormItem label="电话" prop="telephone">
            <Input type="text" v-model="seach.telephone" placeholder="请输入电话号码"></Input>
          </FormItem>
          <FormItem>
            <Button type="primary" icon="ios-search" @click="getusers">搜索</Button>
          </FormItem>
        </Form>
          <Button
          style="margin: -103px 0px 0px 90%;"
            icon="md-add"
            type="success"
            @click="openmodel=true,isadd=true,title='添加用户'"
          >添加客户</Button>
          <Button type="info" icon="ios-undo-outline" @click="exportExcel" style="margin: -143px 1px 1px 85%;">导出</Button>
          <Upload 
          :headers="header"
          :format="format"
          :on-success="importExcel"
          action="http://localhost:8080/users/importexcel">
            <Button type="info" icon="ios-undo" >导入</Button>
          </Upload>
      </div>
      <Table height="600" highlight-row stripe :columns="columns1" :data="data"></Table>
      <div style="float:right;padding-right: 15%;padding-top: 3px;">
        <Page
          @on-change="pagechange"
          @on-page-size-change="changepagesize"
          :total="page.total"
          :page-size="page.size"
          size="small"
          show-elevator
          show-sizer
        />
      </div>
    </Card>
    <Modal
      :title="title"
      v-model="openmodel"
      @on-cancel="Close"
      @on-ok="changbutter(users)"
      :closable="false"
    >
      <Form :model="users" :rules="ruleValidate" :label-width="80">
        <FormItem label="姓名" prop="name">
          <Input v-model="users.name" placeholder="请输入你的名字"></Input>
        </FormItem>
        <FormItem label="年龄" prop="age">
          <InputNumber :max="100" :min="0" v-model="users.age"></InputNumber>
        </FormItem>
        <FormItem label="性别" prop="sex">
          <RadioGroup v-model="users.sex">
            <Radio :label="1">男</Radio>
            <Radio :label="2">女</Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="电话" prop="telephone">
          <Input v-model="users.telephone" placeholder="请输入你的电话"></Input>
        </FormItem>
        <FormItem label="住址" prop="address">
          <Input v-model="users.address" placeholder="请输入你的住址"></Input>
        </FormItem>
        <FormItem label="籍贯" prop="nativeplace">
          <Input v-model="users.nativeplace" placeholder="请输入你的籍贯"></Input>
        </FormItem>
        <FormItem label="加入时间" prop="jointime">
          <DatePicker
            v-model="users.jointime"
            format="yyyy年MM月dd日"
            type="date"
            placeholder="Select date"
            style="width: 200px"
          ></DatePicker>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>
<script>
import { formatDate ,downLoadXls} from "@/libs/utils.js";
import { select, update, deletes, insert,exportexcel } from "@/api/app.js";
import localstorages from '@/libs/localstorage.js'
export default {
  data() {
    return {
      columns1: [
        {
          title: "姓名",
          key: "name"
        },
        {
          title: "年龄",
          key: "age"
        },
        {
          title: "性别",
          key: "sex",
          render: (h, params) => {
            return h("span", params.row.sex == 1 ? "男" : "女");
          }
        },
        {
          title: "电话",
          key: "telephone"
        },
        {
          title: "住址",
          key: "address"
        },
        {
          title: "籍贯",
          key: "nativeplace"
        },
        {
          title: "加入时间",
          render: (h, params) => {
            return h(
              "div",
              formatDate(new Date(params.row.jointime), "yyyy-MM-dd")
            );
          }
        },
        {
          title: "操作",
          align: "center",
          width: 220,
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "primary",
                    size: "default"
                  },
                  style: {
                    marginRight: "5px",
                    display: "inline-block"
                  },
                  on: {
                    click: () => {
                      //点击以后的方法
                      this.title = "修改信息";
                      this.isadd = false;
                      this.users = params.row;
                      this.openmodel = true;
                    }
                  }
                },
                "修改"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "default"
                  },
                  style: {
                    marginRight: "5px"
                  },
                  on: {
                    click: () => {
                      this.$Modal.confirm({
                        title: "系统提示",
                        content: "<p>你丫确定要删除吗？</p>",
                        onOk: () => {
                          this.delete(params.row);
                          this.$Message.info("O J 8 K您已删除成功!!!");
                        },
                        onCancel: () => {}
                      });
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      ruleValidate: {
        name: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "请选择你的性别", trigger: "blur" }],
        telephone: [
          { required: true, message: "请输入你的电话", trigger: "blur" }
        ],
        address: [
          { required: true, message: "请输入你的地址", trigger: "blur" }
        ],
        nativeplace: [
          { required: true, message: "请输入你的籍贯", trigger: "blur" }
        ],
        jointime: [
          {
            required: true,
            type: "date",
            message: "请选择加入时间",
            trigger: "change"
          }
        ]
      },
      format:['xls'],
      header:{
        token:localstorages.get('token')
      },
      isadd: Boolean,
      title: "添加客户",
      open: false,
      openmodel: false,
      data: [],
      page: {
        current: 1,
        total: 0,
        size: 10
      },
      seach: {
        name: "",
        telephone: ""
      },
      users: {
        name: "",
        age: 0,
        sex: undefined,
        telephone: "",
        address: "",
        nativeplace: "",
        jointime: ""
      }
    };
  },
  created: function() {
    this.getusers();
  },
  methods: {
    getusers() {
      select(this.seach, this.page)
        .then(res => {
          this.data = res.data.records;
          this.page.total = res.data.total;
        })
        .catch(() => console.log("promise catch err"));
    },
    pagechange(current) {
      this.page.current = current;
      this.getusers();
    },
    changepagesize(pagesize) {
      this.page.size = pagesize;
      this.getusers();
    },
    changbutter(users) {
      debugger;
      if (this.isadd == true) {
        insert(this.users).then(res => {
          if (res.status == 200) {
            this.Close();
            this.getusers();
            this.$Message.info("添加成功！");
          } else {
            this.Close();
            this.getusers();
            this.$Message.info("添加失败！");
          }
        });
      } else {
        debugger;
        update(users).then(res => {
          if (res.status == 200) {
            this.Close();
            this.getusers();
            this.$Message.info("修改成功！");
          } else {
            this.Close();
            this.getusers();
            this.$Message.info("修改失败！");
          }
        });
      }
      this.getusers();
    },
    Close() {
      this.openmodel = false;
      this.users = {
        name: "",
        age: 0,
        sex: undefined,
        telephone: "",
        address: "",
        nativeplace: "",
        jointime: ""
      };
    },
    delete(users) {
      deletes(users);
      this.getusers();
    },
    exportExcel(){
      exportexcel(this.seach,this.page).then(res=>{
        debugger
        if(res.status==200){
          downLoadXls(res.data,'忍者用户名单')
            this.$Message.info("导出成功！");
        }
      })
    },
    importExcel(response,file,fileList){
      this.$Message.info("导入成功！");
      this.getusers();
    }
  }
}
</script>

<style lang="less" scoped>
/deep/.ivu-card-body {
  padding: 30px;
}
.ivu-upload{
  margin: -98px 0px 0px 80%;
}
</style>
