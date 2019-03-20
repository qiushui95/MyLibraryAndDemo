package me.yangcx.example.sundries

import android.Manifest

object Permissions {
    object Single {
        //读取日历权限
        val READ_CALENDAR by lazy { Manifest.permission.READ_CALENDAR }
        //写入日历权限
        val WRITE_CALENDAR by lazy { Manifest.permission.WRITE_CALENDAR }

        //相机权限
        val CAMERA by lazy { Manifest.permission.CAMERA }

        //读取联系人权限
        val READ_CONTACTS by lazy { Manifest.permission.READ_CONTACTS }
        //写联系人权限
        val WRITE_CONTACTS by lazy { Manifest.permission.WRITE_CONTACTS }
        //获取手机存储账户
        val GET_ACCOUNTS by lazy { Manifest.permission.GET_ACCOUNTS }

        //粗略定位权限
        val ACCESS_FINE_LOCATION by lazy { Manifest.permission.ACCESS_FINE_LOCATION }
        //GPS精准定位权限
        val ACCESS_COARSE_LOCATION by lazy { Manifest.permission.ACCESS_COARSE_LOCATION }

        //麦克风录音权限
        val RECORD_AUDIO by lazy { Manifest.permission.RECORD_AUDIO }

        //读取电话状态权限
        val READ_PHONE_STATE by lazy { Manifest.permission.READ_PHONE_STATE }
        //拨打电话权限
        val CALL_PHONE by lazy { Manifest.permission.CALL_PHONE }
        //读取通话记录权限
        val READ_CALL_LOG by lazy { Manifest.permission.READ_CALL_LOG }
        //写入通话记录权限
        val WRITE_CALL_LOG by lazy { Manifest.permission.WRITE_CALL_LOG }
        //添加语音邮件权限
        val ADD_VOICEMAIL by lazy { Manifest.permission.ADD_VOICEMAIL }
        //使用SIP视频服务权限
        val USE_SIP by lazy { Manifest.permission.USE_SIP }
        //处理播出电话权限
        val PROCESS_OUTGOING_CALLS by lazy { Manifest.permission.PROCESS_OUTGOING_CALLS }

        //使用身体传感器权限
        val BODY_SENSORS by lazy { Manifest.permission.BODY_SENSORS }

        //发送短信权限
        val SEND_SMS by lazy { Manifest.permission.SEND_SMS }
        //接收短信权限
        val RECEIVE_SMS by lazy { Manifest.permission.RECEIVE_SMS }
        //读取短信权限
        val READ_SMS by lazy { Manifest.permission.READ_SMS }
        //接收wap push权限
        val RECEIVE_WAP_PUSH by lazy { Manifest.permission.RECEIVE_WAP_PUSH }
        //接收彩信
        val RECEIVE_MMS by lazy { Manifest.permission.RECEIVE_MMS }

        //读取外部存储权限
        val READ_EXTERNAL_STORAGE by lazy { Manifest.permission.READ_EXTERNAL_STORAGE }
        //写入外部存储权限
        val WRITE_EXTERNAL_STORAGE by lazy { Manifest.permission.WRITE_EXTERNAL_STORAGE }
    }

    object Group {
        //读写日历权限
        val CALENDAR by lazy {
            arrayOf(
                Permissions.Single.READ_CALENDAR,
                Permissions.Single.WRITE_CALENDAR
            )
        }
        //相机权限
        val CAMERA by lazy {
            arrayOf(
                Permissions.Single.CAMERA
            )
        }
        //联系人相关权限
        val CONTACTS by lazy {
            arrayOf(
                Permissions.Single.READ_CONTACTS,
                Permissions.Single.WRITE_CONTACTS,
                Permissions.Single.GET_ACCOUNTS
            )
        }
        //定位权限
        val LOCATION by lazy {
            arrayOf(
                Permissions.Single.ACCESS_FINE_LOCATION,
                Permissions.Single.ACCESS_COARSE_LOCATION
            )
        }
        //录音权限
        val MICROPHONE by lazy {
            arrayOf(
                Permissions.Single.RECORD_AUDIO
            )
        }
        //电话相关权限
        val PHONE by lazy {
            arrayOf(
                Permissions.Single.READ_PHONE_STATE,
                Permissions.Single.CALL_PHONE,
                Permissions.Single.READ_CALL_LOG,
                Permissions.Single.WRITE_CALL_LOG,
                Permissions.Single.ADD_VOICEMAIL,
                Permissions.Single.USE_SIP,
                Permissions.Single.PROCESS_OUTGOING_CALLS
            )
        }
        //身体传感器权限
        val SENSORS by lazy {
            arrayOf(
                Permissions.Single.BODY_SENSORS
            )
        }
        //信息权限
        val SMS by lazy {
            arrayOf(
                Permissions.Single.SEND_SMS,
                Permissions.Single.RECEIVE_SMS,
                Permissions.Single.READ_SMS,
                Permissions.Single.RECEIVE_WAP_PUSH,
                Permissions.Single.RECEIVE_MMS
            )
        }
        //读写储存卡权限
        val STORAGE by lazy {
            arrayOf(
                Permissions.Single.READ_EXTERNAL_STORAGE,
                Permissions.Single.WRITE_EXTERNAL_STORAGE
            )
        }
    }
}