import request from '@/utils/request'

// 查询测试列表
export function listAlarmTest(query) {
  return request({
    url: '/testDev/alarmTest/list',
    method: 'get',
    params: query
  })
}

// 查询测试详细
export function getAlarmTest(id) {
  return request({
    url: '/testDev/alarmTest/' + id,
    method: 'get'
  })
}

// 新增测试
export function addAlarmTest(data) {
  return request({
    url: '/testDev/alarmTest',
    method: 'post',
    data: data
  })
}

// 修改测试
export function updateAlarmTest(data) {
  return request({
    url: '/testDev/alarmTest',
    method: 'put',
    data: data
  })
}

// 删除测试
export function delAlarmTest(id) {
  return request({
    url: '/testDev/alarmTest/' + id,
    method: 'delete'
  })
}
