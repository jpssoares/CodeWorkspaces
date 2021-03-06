text ="""<?xml version="1.0" encoding="utf-8"?>
<theme format-ver="00.99" package="0">
 <HomeProperty>
   <m_bgParam>
     <BackgroundParam>
       <m_waveType>11</m_waveType>
       <m_imageFilePath>bg1.png</m_imageFilePath>
       <m_thumbnailFilePath>bg1t.png</m_thumbnailFilePath>
     </BackgroundParam>
     <BackgroundParam>
       <m_waveType>11</m_waveType>
       <m_imageFilePath>bg2.png</m_imageFilePath>
       <m_thumbnailFilePath>bg2t.png</m_thumbnailFilePath>
     </BackgroundParam>
     <BackgroundParam>
       <m_waveType>11</m_waveType>
       <m_imageFilePath>bg3.png</m_imageFilePath>
       <m_thumbnailFilePath>bg3t.png</m_thumbnailFilePath>
     </BackgroundParam>
     <BackgroundParam>
       <m_waveType>11</m_waveType>
       <m_imageFilePath>bg4.png</m_imageFilePath>
       <m_thumbnailFilePath>bg4t.png</m_thumbnailFilePath>
     </BackgroundParam>
     <BackgroundParam>
       <m_waveType>11</m_waveType>
       <m_imageFilePath>bg5.png</m_imageFilePath>
       <m_thumbnailFilePath>bg5t.png</m_thumbnailFilePath>
     </BackgroundParam>
   </m_bgParam>
    <m_bgmFilePath>BGM.at9</m_bgmFilePath>
   <m_browser>
     <m_iconFilePath>icon_web.png</m_iconFilePath>
   </m_browser>
   <m_calendar>
     <m_iconFilePath>icon_calendar.png</m_iconFilePath>
   </m_calendar>
   <m_camera>
     <m_iconFilePath>icon_photos.png</m_iconFilePath>
   </m_camera>
   <m_email>
     <m_iconFilePath>icon_mail.png</m_iconFilePath>
   </m_email>
   <m_friend>
     <m_iconFilePath>icon_friends.png</m_iconFilePath>
   </m_friend>
   <m_hostCollabo>
     <m_iconFilePath>icon_cma.png</m_iconFilePath>
   </m_hostCollabo>
   <m_message>
     <m_iconFilePath>icon_messages.png</m_iconFilePath>
   </m_message>
   <m_music>
     <m_iconFilePath>icon_music.png</m_iconFilePath>
   </m_music>
   <m_near>
     <m_iconFilePath>icon_near.png</m_iconFilePath>
   </m_near>
   <m_parental>
     <m_iconFilePath>icon_parental.png</m_iconFilePath>
   </m_parental>
   <m_party>
     <m_iconFilePath>icon_party.png</m_iconFilePath>
   </m_party>
   <m_power>
     <m_iconFilePath>icon_power.png</m_iconFilePath>
   </m_power>
   <m_ps3Link>
     <m_iconFilePath>icon_ps3link.png</m_iconFilePath>
   </m_ps3Link>
   <m_ps4Link>
     <m_iconFilePath>icon_ps4link.png</m_iconFilePath>
   </m_ps4Link>
   <m_settings>
     <m_iconFilePath>icon_settings.png</m_iconFilePath>
   </m_settings>
   <m_trophy>
     <m_iconFilePath>icon_trophies.png</m_iconFilePath>
   </m_trophy>
   <m_video>
     <m_iconFilePath>icon_videos.png</m_iconFilePath>
   </m_video>
 </HomeProperty>
 <InfomationBarProperty>
   <m_barColor>ff000000</m_barColor>
   <m_indicatorColor>ffeedfd0</m_indicatorColor>
   <m_noticeFontColor>00020202</m_noticeFontColor>
<!-- Notification-bubble font color -->
    <m_noticeGlowColor>ff020202</m_noticeGlowColor>
<!-- Notification-bubble glow color -->
    <m_noNoticeFilePath>notice.png</m_noNoticeFilePath>
    <m_newNoticeFilePath>notices.png</m_newNoticeFilePath>
 </InfomationBarProperty>
 <InfomationProperty>
   <m_contentVer>01.00</m_contentVer>
   <m_homePreviewFilePath>preview_page.png</m_homePreviewFilePath>
   <m_packageImageFilePath>preview_thumbnail.png</m_packageImageFilePath>
   <m_provider>
     <m_default>PsychAssault</m_default>
     <m_param></m_param>
   </m_provider>
   <m_startPreviewFilePath>preview_lockscreen.png</m_startPreviewFilePath>
   <m_title>
     <m_default>BlackNWhite</m_default>
     <m_param>
       <m_da>BlackNWhite</m_da>
       <m_de>BlackNWhite</m_de>
       <m_es>BlackNWhite</m_es>
       <m_fi>BlackNWhite</m_fi>
       <m_fr>BlackNWhite</m_fr>
       <m_it>BlackNWhite</m_it>
       <m_nl>BlackNWhite</m_nl>
       <m_no>BlackNWhite</m_no>
       <m_pl>BlackNWhite</m_pl>
       <m_pt>BlackNWhite</m_pt>
       <m_ru>BlackNWhite</m_ru>
       <m_sv>BlackNWhite</m_sv>
     </m_param>
   </m_title>
   <m_provider>
      <m_default>PsychAssault</m_default>
   </m_provider>
 </InfomationProperty>
 <StartScreenProperty>
   <m_dateColor>ffffffff</m_dateColor>
   <m_dateLayout>1</m_dateLayout>
   <m_filePath>lockpaper.png</m_filePath>
   <m_notifyBgColor>1fffffff</m_notifyBgColor>
   <m_notifyBorderColor>ffcccccc</m_notifyBorderColor>
   <m_notifyFontColor>ffffffff</m_notifyFontColor>
 </StartScreenProperty>
</theme>"""


file = open("theme.xml","w")
file.write(text)