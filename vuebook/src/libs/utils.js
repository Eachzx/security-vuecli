import localstorages from '@/libs/localstorage.js'



export const initMenu = (router, store)=> {
  let routes=localstorages.get('menuList')
      var fmtRoutes = formatRoutes(routes);
      router.addRoutes(fmtRoutes);
      store.commit('setmenuList', fmtRoutes);

}

export const formatRoutes = (routes)=> {
    let fmRoutes = [];
    routes.forEach(router=> {
      let {
        name,
        path,
        component,
        children,
        meta,
      } = router;
      if (children && children instanceof Array) {
        children = formatRoutes(children);
      }
      let fmRouter = {
        name: name,
        path: path,
        component(resolve){
          if(component){
            require(['../' + component + '.vue'], resolve)
          }
        },
        meta: meta,
        children: children
      };
      fmRoutes.push(fmRouter);
    })
    return fmRoutes;
  }
  
// 时间格式化
export function formatDate (date, fmt) {
  let o = {
    'M+': date.getMonth() + 1, // 月份
    'd+': date.getDate(), // 日
    'h+': date.getHours(), // 小时
    'm+': date.getMinutes(), // 分
    's+': date.getSeconds(), // 秒
    'S': date.getMilliseconds() // 毫秒
  }
  if (/(y+)/.test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))
  }
  for (var k in o) {
      if (new RegExp('(' + k + ')').test(fmt)) {
          fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
      }
  }
  return fmt
}

export function downLoadXls(data, filename) {
  //var blob = new Blob([data], {type: 'application/vnd.ms-excel'})接收的是blob，若接收的是文件流，需要转化一下
if (typeof window.chrome !== 'undefined') {
  // Chrome version
  var link = document.createElement('a');
  link.href = window.URL.createObjectURL(data);
  link.download = filename;
  link.click();
} else if (typeof window.navigator.msSaveBlob !== 'undefined') {
  // IE version
  var blob = new Blob([data], { type: 'application/force-download' });
  window.navigator.msSaveBlob(blob, filename);
} else {
  // Firefox version
  var file = new File([data], filename, { type: 'application/force-download' });
  window.open(URL.createObjectURL(file));
}
}