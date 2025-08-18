# 프로젝트 개요: RetHouse

이 문서는 프로젝트 구조와 향후 개발을 위한 일반적인 할 일 목록을 제공합니다.

## 프로젝트 구조

```
D:/Work_Codes/RetHouse/
├───.gitattributes
├───.gitignore
├───dev_renthouse.groovy
├───mvnw
├───mvnw.cmd
├───pom.xml
├───README.md
├───run-RentHouseService.bat
├───.git/
├───.mvn/
│   └───wrapper/
│       └───maven-wrapper.properties
├───data/
│   ├───rentHouse.mv.db
│   └───rentHouse.trace.db
├───jenkins/
│   └───build-pipeline.txt
└───src/
    ├───main/
    │   ├───java/
    │   │   └───com/
    │   │       └───jjst/
    │   │           └───rentManagement/
    │   │               └───renthouse/
    │   │                   ├───RentHouseApplication.java
    │   │                   ├───config/
    │   │                   │   ├───AuditConfig.java
    │   │                   │   ├───LoginInterceptor.java
    │   │                   │   ├───SQLiteDialect.java
    │   │                   │   ├───WebConfig.java
    │   │                   │   └───WebSecurityConfig.java
    │   │                   ├───controller/
    │   │                   │   ├───AdminController.java
    │   │                   │   └───HomeController.java
    │   │                   ├───dto/
    │   │                   │   ├───LeaseDto.java
    │   │                   │   ├───LoginDto.java
    │   │                   │   ├───MemberDto.java
    │   │                   │   ├───PropertyDto.java
    │   │                   │   └───UnitDto.java
    │   │                   ├───handler/
    │   │                   │   ├───CustomAuthenticationFailureHandler.java
    │   │                   │   └───GlobalExceptionHandler.java
    │   │                   ├───module/
    │   │                   │   ├───bills/
    │   │                   │   │   ├───entity/
    │   │                   │   │   │   └───Bill.java
    │   │                   │   │   ├───repository/
    │   │                   │   │   │   └───BillRepository.java
    │   │                   │   │   └───service/
    │   │                   │   │       └───BillServiceImpl.java
    │   │                   │   ├───common/
    │   │                   │   │   ├───entity/
    │   │                   │   │   │   └───BaseEntity.java
    │   │                   │   │   ├───enums/
    │   │                   │   │   │   ├───BillCategory.java
    │   │                   │   │   │   ├───LeaseStatus.java
    │   │                   │   │   │   ├───LeaseType.java
    │   │                   │   │   │   ├───MembershipType.java
    │   │                   │   │   │   └───PropertyType.java
    │   │                   │   │   └───service/
    │   │                   │   │       └───CustomUserDetailsServiceImpl.java
    │   │                   │   ├───leases/
    │   │                   │   │   ├───entity/
    │   │                   │   │   │   └───Lease.java
    │   │                   │   │   ├───repository/
    │   │                   │   │   │   └───LeaseRepository.java
    │   │                   │   │   └───service/
    │   │                   │   │       └───LeaseServiceImpl.java
    │   │                   │   ├───members/
    │   │                   │   │   ├───entity/
    │   │                   │   │   │   └───Member.java
    │   │                   │   │   ├───repository/
    │   │                   │   │   │   └───MemberRepository.java
    │   │                   │   │   └───service/
    │   │                   │   │       └───MemberServiceImpl.java
    │   │                   │   ├───properties/
    │   │                   │   │   ├───entity/
    │   │                   │   │   │   ├───Property.java
    │   │                   │   │   │   └───Unit.java
    │   │                   │   │   ├───repository/
    │   │                   │   │   │   ├───PropertyRepository.java
    │   │                   │   │   │   └───UnitRepository.java
    │   │                   │   │   └───service/
    │   │                   │   │       └───PropertyServiceImpl.java
    │   │                   │   └───tenants/
    │   │                   │       ├───entity/
    │   │                   │       │   └───Tenant.java
    │   │                   │       ├───repository/
    │   │                   │       │   └───TenantRepository.java
    │   │                   │       └───service/
    │   │                   │           └───TenantServiceImpl.java
    │   │                   ├───restController/
    │   │                   │   ├───AdminRestController.java
    │   │                   │   └───MemberRestController.java
    │   │                   ├───service/
    │   │                   │   ├───BillService.java
    │   │                   │   ├───CustomUserDetailsService.java
    │   │                   │   ├───LeaseService.java
    │   │                   │   ├───MemberService.java
    │   │                   │   ├───PropertyService.java
    │   │                   │   └───TenantService.java
    │   │                   └───util/
    │   │                       ├───EntityConverter.java
    │   │                       ├───SQLiteDialect.java
    │   │                       └───Utility.java
    │   └───resources/
    │       ├───application-dev.properties
    │       ├───application-local.properties
    │       ├───application-prd.properties
    │       ├───application-sqlite.properties
    │       ├───application.properties
    │       ├───application.template.properties
    │       ├───static/
    │       │   ├───.browserslistrc
    │       │   ├───.travis.yml
    │       │   ├───404.html
    │       │   ├───blank.html
    │       │   ├───buttons.html
    │       │   ├───cards.html
    │       │   ├───charts.html
    │       │   ├───favicon.ico
    │       │   ├───forgot-password.html
    │       │   ├───gulpfile.js
    │       │   ├───index.html
    │       │   ├───LICENSE
    │       │   ├───login.html
    │       │   ├───package-lock.json
    │       │   ├───package.json
    │       │   ├───PRO_UPGRADE.txt
    │       │   ├───README.md
    │       │   ├───register.html
    │       │   ├───tables.html
    │       │   ├───utilities-animation.html
    │       │   ├───utilities-border.html
    │       │   ├───utilities-color.html
    │       │   ├───utilities-other.html
    │       │   ├───css/
    │       │   │   ├───sb-admin-2.css
    │       │   │   └───sb-admin-2.min.css
    │       │   ├───img/
    │       │   │   ├───undraw_posting_photo.svg
    │       │   │   ├───undraw_profile_1.svg
    │       │   │   ├───undraw_profile_2.svg
    │       │   │   ├───undraw_profile_3.svg
    │       │   │   ├───undraw_profile.svg
    │       │   │   └───undraw_rocket.svg
    │       │   ├───js/
    │       │   │   ├───sb-admin-2.js
    │       │   │   ├───sb-admin-2.min.js
    │       │   │   └───demo/
    │       │   │       ├───chart-area-demo.js
    │       │   │       ├───chart-bar-demo.js
    │       │   │       ├───chart-pie-demo.js
    │       │   │       └───datatables-demo.js
    │       │   ├───scss/
    │       │   │   ├───_buttons.scss
    │       │   │   ├───_cards.scss
    │       │   │   ├───_charts.scss
    │       │   │   ├───_dropdowns.scss
    │       │   │   ├───_error.scss
    │       │   │   ├───_footer.scss
    │       │   │   ├───_global.scss
    │       │   │   ├───_login.scss
    │       │   │   ├───_mixins.scss
    │       │   │   ├───_navs.scss
    │       │   │   ├───_utilities.scss
    │       │   │   ├───_variables.scss
    │       │   │   ├───sb-admin-2.scss
    │       │   │   ├───navs/
    │       │   │   │   ├───_global.scss
    │       │   │   │   ├───_sidebar.scss
    │       │   │   │   └───_topbar.scss
    │       │   │   └───utilities/
    │       │   │       ├───_animation.scss
    │       │   │       ├───_background.scss
    │       │   │       ├───_border.scss
    │       │   │       ├───_display.scss
    │       │   │       ├───_progress.scss
    │       │   │       ├───_rotate.scss
    │       │   │       └───_text.scss
    │       │   └───vendor/
    │       │       ├───bootstrap/
    │       │       │   ├───js/
    │       │       │   │   ├───bootstrap.bundle.js
    │       │       │   │   ├───bootstrap.bundle.js.map
    │       │       │   │   ├───bootstrap.bundle.min.js
    │       │       │   │   ├───bootstrap.bundle.min.js.map
    │       │       │   │   ├───bootstrap.js
    │       │       │   │   ├───bootstrap.js.map
    │       │       │   │   ├───bootstrap.min.js
    │       │       │   │   └───bootstrap.min.js.map
    │       │       │   └───scss/
    │       │       │       ├───_alert.scss
    │       │       │       ├───_badge.scss
    │       │       │       ├───_breadcrumb.scss
    │       │       │       ├───_button-group.scss
    │       │       │       ├───_buttons.scss
    │       │       │       ├───_card.scss
    │       │       │       ├───_carousel.scss
    │       │       │       ├───_close.scss
    │       │       │       ├───_code.scss
    │       │       │       ├───_custom-forms.scss
    │       │       │       ├───_dropdown.scss
    │       │       │       ├───_forms.scss
    │       │       │       ├───_functions.scss
    │       │       │       ├───_images.scss
    │       │       │       ├───_input-group.scss
    │       │       │       ├───_jumbotron.scss
    │       │       │       ├───_list-group.scss
    │       │       │       ├───_media.scss
    │       │       │       ├───_mixins.scss
    │       │       │       ├───_modal.scss
    │       │       │       ├───_nav.scss
    │       │       │       ├───_navbar.scss
    │       │       │       ├───_pagination.scss
    │       │       │       ├───_popover.scss
    │       │       │       ├───_print.scss
    │       │       │       ├───_progress.scss
    │       │       │       ├───_reboot.scss
    │       │       │       ├───_root.scss
    │       │       │       ├───_spinners.scss
    │       │       │       ├───_tables.scss
    │       │       │       ├───_toasts.scss
    │       │       │       ├───_tooltip.scss
    │       │       │       ├───_transitions.scss
    │       │       │       ├───_type.scss
    │       │       │       ├───_utilities.scss
    │       │       │       └───_variables.scss
    │       │       ├───chart.js/
    │       │       │   ├───Chart.bundle.js
    │       │       │   ├───Chart.bundle.min.js
    │       │       │   ├───Chart.js
    │       │       │   └───Chart.min.js
    │       │       ├───datatables/
    │       │       │   ├───dataTables.bootstrap4.css
    │       │       │   ├───dataTables.bootstrap4.js
    │       │       │   ├───dataTables.bootstrap4.min.css
    │       │       │   ├───dataTables.bootstrap4.min.js
    │       │       │   ├───jquery.dataTables.js
    │       │       │   └───jquery.dataTables.min.js
    │       │       ├───fontawesome-free/
    │       │       │   ├───attribution.js
    │       │       │   ├───LICENSE.txt
    │       │       │   ├───package.json
    │       │       │   ├───css/
    │       │       │   │   ├───all.css
    │       │       │   │   ├───all.min.css
    │       │       │   │   ├───brands.css
    │       │       │   │   ├───brands.min.css
    │       │       │   │   ├───fontawesome.css
    │       │       │   │   ├───fontawesome.min.css
    │       │       │   │   ├───regular.css
    │       │       │   │   ├───regular.min.css
    │       │       │   │   ├───solid.css
    │       │       │   │   ├───solid.min.css
    │       │       │   │   ├───svg-with-js.css
    │       │       │   │   ├───svg-with-js.min.css
    │       │       │   │   ├───v4-shims.css
    │       │       │   │   └───v4-shims.min.css
    │       │       │   ├───js/
    │       │       │   │   ├───all.js
    │       │       │   │   ├───all.min.js
    │       │       │   │   ├───brands.js
    │       │       │   │   ├───brands.min.js
    │       │       │   │   ├───conflict-detection.js
    │       │       │   │   ├───conflict-detection.min.js
    │       │       │   │   ├───fontawesome.js
    │       │       │   │   ├───fontawesome.min.js
    │       │       │   │   ├───regular.js
    │       │       │   │   ├───regular.min.js
    │       │       │   │   ├───solid.js
    │       │       │   │   ├───solid.min.js
    │       │       │   │   ├───v4-shims.js
    │       │       │   │   └───v4-shims.min.js
    │       │       │   ├───less/
    │       │       │   │   ├───_animated.less
    │       │       │   │   ├───_bordered-pulled.less
    │       │       │   │   ├───_core.less
    │       │       │   │   ├───_fixed-width.less
    │       │       │   │   ├───_icons.less
    │       │       │   │   ├───_larger.less
    │       │       │   │   ├───_list.less
    │       │       │   │   ├───_mixins.less
    │       │       │   │   ├───_rotated-flipped.less
    │       │       │   │   ├───_screen-reader.less
    │       │       │   │   ├───_shims.less
    │       │       │   │   ├───_stacked.less
    │       │       │   │   ├───_variables.less
    │       │       │   │   ├───brands.less
    │       │       │   │   ├───fontawesome.less
    │       │       │   │   ├───regular.less
    │       │       │   │   ├───solid.less
    │       │       │   │   └───v4-shims.less
    │       │       │   ├───metadata/
    │       │       │   │   ├───categories.yml
    │       │       │   │   ├───icons.yml
    │       │       │   │   ├───shims.yml
    │       │       │   │   └───sponsors.yml
    │       │       │   ├───scss/
    │       │       │   │   ├───_animated.scss
    │       │       │   │   ├───_bordered-pulled.scss
    │       │       │   │   ├───_core.scss
    │       │       │   │   ├───_fixed-width.scss
    │       │       │   │   ├───_icons.scss
    │       │       │   │   ├───_larger.scss
    │       │       │   │   ├───_list.scss
    │       │       │   │   ├───_mixins.scss
    │       │       │   │   ├───_rotated-flipped.scss
    │       │       │   │   ├───_screen-reader.scss
    │       │       │   │   ├───_shims.scss
    │       │       │   │   ├───_stacked.scss
    │       │       │   │   ├───_variables.scss
    │       │       │   │   ├───brands.scss
    │       │       │   │   ├───fontawesome.scss
    │       │       │   │   ├───regular.scss
    │       │       │   │   ├───solid.scss
    │       │       │   │   └───v4-shims.scss
    │       │       │   ├───sprites/
    │       │       │   │   ├───brands.svg
    │       │       │   │   ├───regular.svg
    │       │       │   │   └───solid.svg
    │       │       │   ├───svgs/
    │       │       │   │   ├───brands/
    │       │       │   │   │   ├───500px.svg
    │       │       │   │   │   ├───accessible-icon.svg
    │       │       │   │   │   ├───accusoft.svg
    │       │       │   │   │   ├───acquisitions-incorporated.svg
    │       │       │   │   │   ├───adn.svg
    │       │       │   │   │   ├───adversal.svg
    │       │       │   │   │   ├───airbnb.svg
    │       │       │   │   │   ├───algolia.svg
    │       │       │   │   │   ├───alipay.svg
    │       │       │   │   │   ├───amazon.svg
    │       │       │   │   │   ├───amazon-pay.svg
    │       │       │   │   │   ├───amilia.svg
    │       │       │   │   │   ├───android.svg
    │       │       │   │   │   ├───angellist.svg
    │       │       │   │   │   ├───angrycreative.svg
    │       │       │   │   │   ├───angular.svg
    │       │       │   │   │   ├───app-store.svg
    │       │       │   │   │   ├───app-store-ios.svg
    │       │       │   │   │   ├───apper.svg
    │       │       │   │   │   ├───apple.svg
    │       │       │   │   │   ├───apple-pay.svg
    │       │       │   │   │   ├───artstation.svg
    │       │       │   │   │   ├───asymmetrik.svg
    │       │       │   │   │   ├───atlassian.svg
    │       │       │   │   │   ├───audible.svg
    │       │       │   │   │   ├───autoprefixer.svg
    │       │       │   │   │   ├───avianex.svg
    │       │       │   │   │   ├───aviato.svg
    │       │       │   │   │   ├───aws.svg
    │       │       │   │   │   ├───bandcamp.svg
    │       │       │   │   │   ├───battle-net.svg
    │       │       │   │   │   ├───behance.svg
    │       │       │   │   │   ├───behance-square.svg
    │       │       │   │   │   ├───bimobject.svg
    │       │       │   │   │   ├───bitbucket.svg
    │       │       │   │   │   ├───bitcoin.svg
    │       │       │   │   │   ├───bity.svg
    │       │       │   │   │   ├───black-tie.svg
    │       │       │   │   │   ├───blackberry.svg
    │       │       │   │   │   ├───blogger.svg
    │       │       │   │   │   ├───blogger-b.svg
    │       │       │   │   │   ├───bluetooth.svg
    │       │       │   │   │   ├───bluetooth-b.svg
    │       │       │   │   │   ├───bootstrap.svg
    │       │       │   │   │   ├───btc.svg
    │       │       │   │   │   ├───buffer.svg
    │       │       │   │   │   ├───buromobelexperte.svg
    │       │       │   │   │   ├───buy-n-large.svg
    │       │       │   │   │   ├───buysellads.svg
    │       │       │   │   │   ├───canadian-maple-leaf.svg
    │       │       │   │   │   ├───cc-amazon-pay.svg
    │       │       │   │   │   ├───cc-amex.svg
    │       │       │   │   │   ├───cc-apple-pay.svg
    │       │       │   │   │   ├───cc-diners-club.svg
    │       │       │   │   │   ├───cc-discover.svg
    │       │       │   │   │   ├───cc-jcb.svg
    │       │       │   │   │   ├───cc-mastercard.svg
    │       │       │   │   │   ├───cc-paypal.svg
    │       │       │   │   │   ├───cc-stripe.svg
    │       │       │   │   │   ├───cc-visa.svg
    │       │       │   │   │   ├───centercode.svg
    │       │       │   │   │   ├───centos.svg
    │       │       │   │   │   ├───chrome.svg
    │       │       │   │   │   ├───chromecast.svg
    │       │       │   │   │   ├───cloudflare.svg
    │       │       │   │   │   ├───cloudscale.svg
    │       │       │   │   │   ├───cloudsmith.svg
    │       │       │   │   │   ├───cloudversify.svg
    │       │       │   │   │   ├───codepen.svg
    │       │       │   │   │   ├───codiepie.svg
    │       │       │   │   │   ├───confluence.svg
    │       │       │   │   │   ├───connectdevelop.svg
    │       │       │   │   │   ├───contao.svg
    │       │       │   │   │   ├───cotton-bureau.svg
    │       │       │   │   │   ├───cpanel.svg
    │       │       │   │   │   ├───creative-commons.svg
    │       │       │   │   │   ├───creative-commons-alt.svg
    │       │       │   │   │   ├───creative-commons-by.svg
    │       │       │   │   │   ├───creative-commons-nc.svg
    │       │       │   │   │   ├───creative-commons-nc-eu.svg
    │       │       │   │   │   ├───creative-commons-nc-jp.svg
    │       │       │   │   │   ├───creative-commons-nd.svg
    │       │       │   │   │   ├───creative-commons-pd.svg
    │       │       │   │   │   ├───creative-commons-pd-alt.svg
    │       │       │   │   │   ├───creative-commons-remix.svg
    │       │       │   │   │   ├───creative-commons-sa.svg
    │       │       │   │   │   ├───creative-commons-sampling.svg
    │       │       │   │   │   ├───creative-commons-sampling-plus.svg
    │       │       │   │   │   ├───creative-commons-share.svg
    │       │       │   │   │   ├───creative-commons-zero.svg
    │       │       │   │   │   ├───critical-role.svg
    │       │       │   │   │   ├───css3.svg
    │       │       │   │   │   ├───css3-alt.svg
    │       │       │   │   │   ├───cuttlefish.svg
    │       │       │   │   │   ├───d-and-d.svg
    │       │       │   │   │   ├───d-and-d-beyond.svg
    │       │       │   │   │   ├───dailymotion.svg
    │       │       │   │   │   ├───dashcube.svg
    │       │       │   │   │   ├───deezer.svg
    │       │       │   │   │   ├───delicious.svg
    │       │       │   │   │   ├───deploydog.svg
    │       │       │   │   │   ├───deskpro.svg
    │       │       │   │   │   ├───dev.svg
    │       │       │   │   │   ├───deviantart.svg
    │       │       │   │   │   ├───dhl.svg
    │       │       │   │   │   ├───diaspora.svg
    │       │       │   │   │   ├───digg.svg
    │       │       │   │   │   ├───digital-ocean.svg
    │       │       │   │   │   ├───discord.svg
    │       │       │   │   │   ├───discourse.svg
    │       │       │   │   │   ├───dochub.svg
    │       │       │   │   │   ├───docker.svg
    │       │       │   │   │   ├───draft2digital.svg
    │       │       │   │   │   ├───dribbble.svg
    │       │       │   │   │   ├───dribbble-square.svg
    │       │       │   │   │   ├───dropbox.svg
    │       │       │   │   │   ├───drupal.svg
    │       │       │   │   │   ├───dyalog.svg
    │       │       │   │   │   ├───earlybirds.svg
    │       │       │   │   │   ├───ebay.svg
    │       │       │   │   │   ├───edge.svg
    │       │       │   │   │   ├───edge-legacy.svg
    │       │       │   │   │   ├───elementor.svg
    │       │       │   │   │   ├───ello.svg
    │       │       │   │   │   ├───ember.svg
    │       │       │   │   │   ├───empire.svg
    │       │       │   │   │   ├───envira.svg
    │       │       │   │   │   ├───erlang.svg
    │       │       │   │   │   ├───ethereum.svg
    │       │       │   │   │   ├───etsy.svg
    │       │       │   │   │   ├───evernote.svg
    │       │       │   │   │   ├───expeditedssl.svg
    │       │       │   │   │   ├───facebook.svg
    │       │       │   │   │   ├───facebook-f.svg
    │       │       │   │   │   ├───facebook-messenger.svg
    │       │       │   │   │   ├───facebook-square.svg
    │       │       │   │   │   ├───fantasy-flight-games.svg
    │       │       │   │   │   ├───fedex.svg
    │       │       │   │   │   ├───fedora.svg
    │       │       │   │   │   ├───figma.svg
    │       │       │   │   │   ├───firefox.svg
    │       │       │   │   │   ├───firefox-browser.svg
    │       │       │   │   │   ├───first-order.svg
    │       │       │   │   │   ├───first-order-alt.svg
    │       │       │   │   │   ├───firstdraft.svg
    │       │       │   │   │   ├───flickr.svg
    │       │       │   │   │   ├───flipboard.svg
    │       │       │   │   │   ├───fly.svg
    │       │       │   │   │   ├───font-awesome.svg
    │       │       │   │   │   ├───font-awesome-alt.svg
    │       │       │   │   │   ├───font-awesome-flag.svg
    │       │       │   │   │   ├───font-awesome-logo-full.svg
    │       │       │   │   │   ├───fonticons.svg
    │       │       │   │   │   ├───fonticons-fi.svg
    │       │       │   │   │   ├───fort-awesome.svg
    │       │       │   │   │   ├───fort-awesome-alt.svg
    │       │       │   │   │   ├───forumbee.svg
    │       │       │   │   │   ├───foursquare.svg
    │       │       │   │   │   ├───free-code-camp.svg
    │       │       │   │   │   ├───freebsd.svg
    │       │       │   │   │   ├───fulcrum.svg
    │       │       │   │   │   ├───galactic-republic.svg
    │       │       │   │   │   ├───galactic-senate.svg
    │       │       │   │   │   ├───get-pocket.svg
    │       │       │   │   │   ├───gg.svg
    │       │       │   │   │   ├───gg-circle.svg
    │       │       │   │   │   ├───git.svg
    │       │       │   │   │   ├───git-alt.svg
    │       │       │   │   │   ├───git-square.svg
    │       │       │   │   │   ├───github.svg
    │       │       │   │   │   ├───github-alt.svg
    │       │       │   │   │   ├───github-square.svg
    │       │       │   │   │   ├───gitkraken.svg
    │       │       │   │   │   ├───gitlab.svg
    │       │       │   │   │   ├───gitter.svg
    │       │       │   │   │   ├───glide.svg
    │       │       │   │   │   ├───glide-g.svg
    │       │       │   │   │   ├───gofore.svg
    │       │       │   │   │   ├───goodreads.svg
    │       │       │   │   │   ├───goodreads-g.svg
    │       │       │   │   │   ├───google.svg
    │       │       │   │   │   ├───google-drive.svg
    │       │       │   │   │   ├───google-pay.svg
    │       │       │   │   │   ├───google-play.svg
    │       │       │   │   │   ├───google-plus.svg
    │       │       │   │   │   ├───google-plus-g.svg
    │       │       │   │   │   ├───google-plus-square.svg
    │       │       │   │   │   ├───google-wallet.svg
    │       │       │   │   │   ├───gratipay.svg
    │       │       │   │   │   ├───grav.svg
    │       │       │   │   │   ├───gripfire.svg
    │       │       │   │   │   ├───grunt.svg
    │       │       │   │   │   ├───guilded.svg
    │       │       │   │   │   ├───gulp.svg
    │       │       │   │   │   ├───hacker-news.svg
    │       │       │   │   │   ├───hacker-news-square.svg
    │       │       │   │   │   ├───hackerrank.svg
    │       │       │   │   │   ├───hips.svg
    │       │       │   │   │   ├───hire-a-helper.svg
    │       │       │   │   │   ├───hive.svg
    │       │       │   │   │   ├───hooli.svg
    │       │       │   │   │   ├───hornbill.svg
    │       │       │   │   │   ├───hotjar.svg
    │       │       │   │   │   ├───houzz.svg
    │       │       │   │   │   ├───html5.svg
    │       │       │   │   │   ├───hubspot.svg
    │       │       │   │   │   ├───ideal.svg
    │       │       │   │   │   ├───imdb.svg
    │       │       │   │   │   ├───innosoft.svg
    │       │       │   │   │   ├───instagram.svg
    │       │       │   │   │   ├───instagram-square.svg
    │       │       │   │   │   ├───instalod.svg
    │       │       │   │   │   ├───intercom.svg
    │       │       │   │   │   ├───internet-explorer.svg
    │       │       │   │   │   ├───invision.svg
    │       │       │   │   │   ├───ioxhost.svg
    │       │       │   │   │   ├───itch-io.svg
    │       │       │   │   │   ├───itunes.svg
    │       │       │   │   │   ├───itunes-note.svg
    │       │       │   │   │   ├───java.svg
    │       │       │   │   │   ├───jedi-order.svg
    │       │       │   │   │   ├───jenkins.svg
    │       │       │   │   │   ├───jira.svg
    │       │       │   │   │   ├───joget.svg
    │       │       │   │   │   ├───joomla.svg
    │       │       │   │   │   ├───js.svg
    │       │       │   │   │   ├───js-square.svg
    │       │       │   │   │   ├───jsfiddle.svg
    │       │       │   │   │   ├───kaggle.svg
    │       │       │   │   │   ├───keybase.svg
    │       │       │   │   │   ├───keycdn.svg
    │       │       │   │   │   ├───kickstarter.svg
    │       │       │   │   │   ├───kickstarter-k.svg
    │       │       │   │   │   ├───korvue.svg
    │       │       │   │   │   ├───laravel.svg
    │       │       │   │   │   ├───lastfm.svg
    │       │       │   │   │   ├───lastfm-square.svg
    │       │       │   │   │   ├───leanpub.svg
    │       │       │   │   │   ├───less.svg
    │       │       │   │   │   ├───line.svg
    │       │       │   │   │   ├───linkedin.svg
    │       │       │   │   │   ├───linkedin-in.svg
    │       │       │   │   │   ├───linode.svg
    │       │       │   │   │   ├───linux.svg
    │       │       │   │   │   ├───lyft.svg
    │       │       │   │   │   ├───magento.svg
    │       │       │   │   │   ├───mailchimp.svg
    │       │       │   │   │   ├───mandalorian.svg
    │       │       │   │   │   ├───markdown.svg
    │       │       │   │   │   ├───mastodon.svg
    │       │       │   │   │   ├───maxcdn.svg
    │       │       │   │   │   ├───mdb.svg
    │       │       │   │   │   ├───medapps.svg
    │       │       │   │   │   ├───medium.svg
    │       │       │   │   │   ├───medium-m.svg
    │       │       │   │   │   ├───medrt.svg
    │       │       │   │   │   ├───meetup.svg
    │       │       │   │   │   ├───megaport.svg
    │       │       │   │   │   ├───mendeley.svg
    │       │       │   │   │   ├───microsoft.svg
    │       │       │   │   │   ├───microblog.svg
    │       │       │   │   │   ├───mix.svg
    │       │       │   │   │   ├───mixcloud.svg
    │       │       │   │   │   ├───mixer.svg
    │       │       │   │   │   ├───mizuni.svg
    │       │       │   │   │   ├───modx.svg
    │       │       │   │   │   ├───monero.svg
    │       │       │   │   │   ├───napster.svg
    │       │       │   │   │   ├───neos.svg
    │       │       │   │   │   ├───nimblr.svg
    │       │       │   │   │   ├───node.svg
    │       │       │   │   │   ├───node-js.svg
    │       │       │   │   │   ├───npm.svg
    │       │       │   │   │   ├───ns8.svg
    │       │       │   │   │   ├───nutritionix.svg
    │       │       │   │   │   ├───octopus-deploy.svg
    │       │       │   │   │   ├───odnoklassniki.svg
    │       │       │   │   │   ├───odnoklassniki-square.svg
    │       │       │   │   │   ├───old-republic.svg
    │       │       │   │   │   ├───opencart.svg
    │       │       │   │   │   ├───openid.svg
    │       │       │   │   │   ├───opera.svg
    │       │       │   │   │   ├───optin-monster.svg
    │       │       │   │   │   ├───orcid.svg
    │       │       │   │   │   ├───osi.svg
    │       │       │   │   │   ├───page4.svg
    │       │       │   │   │   ├───pagelines.svg
    │       │       │   │   │   ├───palfed.svg
    │       │       │   │   │   ├───patreon.svg
    │       │       │   │   │   ├───paypal.svg
    │       │       │   │   │   ├───penny-arcade.svg
    │       │       │   │   │   ├───perbyte.svg
    │       │       │   │   │   ├───periscope.svg
    │       │       │   │   │   ├───phabricator.svg
    │       │       │   │   │   ├───phoenix-framework.svg
    │       │       │   │   │   ├───phoenix-squadron.svg
    │       │       │   │   │   ├───php.svg
    │       │       │   │   │   ├───pied-piper.svg
    │       │       │   │   │   ├───pied-piper-alt.svg
    │       │       │   │   │   ├───pied-piper-hat.svg
    │       │       │   │   │   ├───pied-piper-pp.svg
    │       │       │   │   │   ├───pied-piper-square.svg
    │       │       │   │   │   ├───pinterest.svg
    │       │       │   │   │   ├───pinterest-p.svg
    │       │       │   │   │   ├───pinterest-square.svg
    │       │       │   │   │   ├───playstation.svg
    │       │       │   │   │   ├───product-hunt.svg
    │       │       │   │   │   ├───pushed.svg
    │       │       │   │   │   ├───python.svg
    │       │       │   │   │   ├───qq.svg
    │       │       │   │   │   ├───quinscape.svg
    │       │       │   │   │   ├───quora.svg
    │       │       │   │   │   ├───r-project.svg
    │       │       │   │   │   ├───raspberry-pi.svg
    │       │       │   │   │   ├───ravelry.svg
    │       │       │   │   │   ├───react.svg
    │       │       │   │   │   ├───reacteurope.svg
    │       │       │   │   │   ├───readme.svg
    │       │       │   │   │   ├───rebel.svg
    │       │       │   │   │   ├───red-river.svg
    │       │       │   │   │   ├───reddit.svg
    │       │       │   │   │   ├───reddit-alien.svg
    │       │       │   │   │   ├───reddit-square.svg
    │       │       │   │   │   ├───redhat.svg
    │       │       │   │   │   ├───renren.svg
    │       │       │   │   │   ├───replyd.svg
    │       │       │   │   │   ├───researchgate.svg
    │       │       │   │   │   ├───resolving.svg
    │       │       │   │   │   ├───rev.svg
    │       │       │   │   │   ├───rocketchat.svg
    │       │       │   │   │   ├───rockrms.svg
    │       │       │   │   │   ├───rust.svg
    │       │       │   │   │   ├───safari.svg
    │       │       │   │   │   ├───salesforce.svg
    │       │       │   │   │   ├───sass.svg
    │       │       │   │   │   ├───schlix.svg
    │       │       │   │   │   ├───scribd.svg
    │       │       │   │   │   ├───searchengin.svg
    │       │       │   │   │   ├───sellsy.svg
    │       │       │   │   │   ├───servicestack.svg
    │       │       │   │   │   ├───shirtsinbulk.svg
    │       │       │   │   │   ├───shopify.svg
    │       │       │   │   │   ├───shopware.svg
    │       │       │   │   │   ├───simplybuilt.svg
    │       │       │   │   │   ├───sistrix.svg
    │       │       │   │   │   ├───sith.svg
    │       │       │   │   │   ├───sketch.svg
    │       │       │   │   │   ├───skyatlas.svg
    │       │       │   │   │   ├───skype.svg
    │       │       │   │   │   ├───slack.svg
    │       │       │   │   │   ├───slack-hash.svg
    │       │       │   │   │   ├───slideshare.svg
    │       │       │   │   │   ├───snapchat.svg
    │       │       │   │   │   ├───snapchat-ghost.svg
    │       │       │   │   │   ├───snapchat-square.svg
    │       │       │   │   │   ├───soundcloud.svg
    │       │       │   │   │   ├───sourcetree.svg
    │       │       │   │   │   ├───speakap.svg
    │       │       │   │   │   ├───speaker-deck.svg
    │       │       │   │   │   ├───spotify.svg
    │       │       │   │   │   ├───squarespace.svg
    │       │       │   │   │   ├───stack-exchange.svg
    │       │       │   │   │   ├───stack-overflow.svg
    │       │       │   │   │   ├───stackpath.svg
    │       │       │   │   │   ├───staylinked.svg
    │       │       │   │   │   ├───steam.svg
    │       │       │   │   │   ├───steam-square.svg
    │       │       │   │   │   ├───steam-symbol.svg
    │       │       │   │   │   ├───sticker-mule.svg
    │       │       │   │   │   ├───strava.svg
    │       │       │   │   │   ├───stripe.svg
    │       │       │   │   │   ├───stripe-s.svg
    │       │       │   │   │   ├───studiovinari.svg
    │       │       │   │   │   ├───stumbleupon.svg
    │       │       │   │   │   ├───stumbleupon-circle.svg
    │       │       │   │   │   ├───superpowers.svg
    │       │       │   │   │   ├───supple.svg
    │       │       │   │   │   ├───suse.svg
    │       │       │   │   │   ├───swift.svg
    │       │       │   │   │   ├───symfony.svg
    │       │       │   │   │   ├───teamspeak.svg
    │       │       │   │   │   ├───telegram.svg
    │       │       │   │   │   ├───telegram-plane.svg
    │       │       │   │   │   ├───tencent-weibo.svg
    │       │       │   │   │   ├───the-red-yeti.svg
    │       │       │   │   │   ├───themeco.svg
    │       │       │   │   │   ├───themeisle.svg
    │       │       │   │   │   ├───think-peaks.svg
    │       │       │   │   │   ├───tiktok.svg
    │       │       │   │   │   ├───trade-federation.svg
    │       │       │   │   │   ├───trello.svg
    │       │       │   │   │   ├───tripadvisor.svg
    │       │       │   │   │   ├───tumblr.svg
    │       │       │   │   │   ├───tumblr-square.svg
    │       │       │   │   │   ├───twitch.svg
    │       │       │   │   │   ├───twitter.svg
    │       │       │   │   │   ├───twitter-square.svg
    │       │       │   │   │   ├───typo3.svg
    │       │       │   │   │   ├───uber.svg
    │       │       │   │   │   ├───ubuntu.svg
    │       │       │   │   │   ├───uikit.svg
    │       │       │   │   │   ├───umbraco.svg
    │       │       │   │   │   ├───uncharted.svg
    │       │       │   │   │   ├───uniregistry.svg
    │       │       │   │   │   ├───unity.svg
    │       │       │   │   │   ├───unsplash.svg
    │       │       │   │   │   ├───untappd.svg
    │       │       │   │   │   ├───ups.svg
    │       │       │   │   │   ├───usb.svg
    │       │       │   │   │   ├───usps.svg
    │       │       │   │   │   ├───ussunnah.svg
    │       │       │   │   │   ├───vaadin.svg
    │       │       │   │   │   ├───viacoin.svg
    │       │       │   │   │   ├───viadeo.svg
    │       │       │   │   │   ├───viadeo-square.svg
    │       │       │   │   │   ├───viber.svg
    │       │       │   │   │   ├───vimeo.svg
    │       │       │   │   │   ├───vimeo-square.svg
    │       │       │   │   │   ├───vimeo-v.svg
    │       │       │   │   │   ├───vine.svg
    │       │       │   │   │   ├───vk.svg
    │       │       │   │   │   ├───vnv.svg
    │       │       │   │   │   ├───vuejs.svg
    │       │       │   │   │   ├───watchman-monitoring.svg
    │       │       │   │   │   ├───waze.svg
    │       │       │   │   │   ├───weebly.svg
    │       │       │   │   │   ├───weibo.svg
    │       │       │   │   │   ├───weixin.svg
    │       │       │   │   │   ├───whatsapp.svg
    │       │       │   │   │   ├───whatsapp-square.svg
    │       │       │   │   │   ├───whmcs.svg
    │       │       │   │   │   ├───wikipedia-w.svg
    │       │       │   │   │   ├───windows.svg
    │       │       │   │   │   ├───wix.svg
    │       │       │   │   │   ├───wizards-of-the-coast.svg
    │       │       │   │   │   ├───wodu.svg
    │       │       │   │   │   ├───wolf-pack-battalion.svg
    │       │       │   │   │   ├───wordpress.svg
    │       │       │   │   │   ├───wordpress-simple.svg
    │       │       │   │   │   ├───wpexplorer.svg
    │       │       │   │   │   ├───wpforms.svg
    │       │       │   │   │   ├───wpressr.svg
    │       │       │   │   │   ├───xbox.svg
    │       │       │   │   │   ├───xing.svg
    │       │       │   │   │   ├───xing-square.svg
    │       │       │   │   │   ├───y-combinator.svg
    │       │       │   │   │   ├───yahoo.svg
    │       │       │   │   │   ├───yammer.svg
    │       │       │   │   │   ├───yandex.svg
    │       │       │   │   │   ├───yandex-international.svg
    │       │       │   │   │   ├───yarn.svg
    │       │       │   │   │   ├───yelp.svg
    │       │       │   │   │   ├───yoast.svg
    │       │       │   │   │   ├───youtube.svg
    │       │       │   │   │   ├───youtube-square.svg
    │       │       │   │   │   └───zhihu.svg
    │       │       │   │   ├───regular/
    │       │       │   │   │   ├───address-book.svg
    │       │       │   │   │   ├───address-card.svg
    │       │       │   │   │   ├───angry.svg
    │       │       │   │   │   ├───arrow-alt-circle-down.svg
    │       │       │   │   │   ├───arrow-alt-circle-left.svg
    │       │       │   │   │   ├───arrow-alt-circle-right.svg
    │       │       │   │   │   ├───arrow-alt-circle-up.svg
    │       │       │   │   │   ├───bell.svg
    │       │       │   │   │   ├───bell-slash.svg
    │       │       │   │   │   ├───bookmark.svg
    │       │       │   │   │   ├───building.svg
    │       │       │   │   │   ├───calendar.svg
    │       │       │   │   │   ├───calendar-alt.svg
    │       │       │   │   │   ├───calendar-check.svg
    │       │       │   │   │   ├───calendar-minus.svg
    │       │       │   │   │   ├───calendar-plus.svg
    │       │       │   │   │   ├───calendar-times.svg
    │       │       │   │   │   ├───caret-square-down.svg
    │       │       │   │   │   ├───caret-square-left.svg
    │       │       │   │   │   ├───caret-square-right.svg
    │       │       │   │   │   ├───caret-square-up.svg
    │       │       │   │   │   ├───chart-bar.svg
    │       │       │   │   │   ├───check-circle.svg
    │       │       │   │   │   ├───check-square.svg
    │       │       │   │   │   ├───circle.svg
    │       │       │   │   │   ├───clipboard.svg
    │       │       │   │   │   ├───clock.svg
    │       │       │   │   │   ├───clone.svg
    │       │       │   │   │   ├───closed-captioning.svg
    │       │       │   │   │   ├───comment.svg
    │       │       │   │   │   ├───comment-alt.svg
    │       │       │   │   │   ├───comment-dots.svg
    │       │       │   │   │   ├───comments.svg
    │       │       │   │   │   ├───compass.svg
    │       │       │   │   │   ├───copy.svg
    │       │       │   │   │   ├───copyright.svg
    │       │       │   │   │   ├───credit-card.svg
    │       │       │   │   │   ├───dizzy.svg
    │       │       │   │   │   ├───dot-circle.svg
    │       │       │   │   │   ├───edit.svg
    │       │       │   │   │   ├───envelope.svg
    │       │       │   │   │   ├───envelope-open.svg
    │       │       │   │   │   ├───eye.svg
    │       │       │   │   │   ├───eye-slash.svg
    │       │       │   │   │   ├───file.svg
    │       │       │   │   │   ├───file-alt.svg
    │       │       │   │   │   ├───file-archive.svg
    │       │       │   │   │   ├───file-audio.svg
    │       │       │   │   │   ├───file-code.svg
    │       │       │   │   │   ├───file-excel.svg
    │       │       │   │   │   ├───file-image.svg
    │       │       │   │   │   ├───file-pdf.svg
    │       │       │   │   │   ├───file-powerpoint.svg
    │       │       │   │   │   ├───file-video.svg
    │       │       │   │   │   ├───file-word.svg
    │       │       │   │   │   ├───flag.svg
    │       │       │   │   │   ├───flushed.svg
    │       │       │   │   │   ├───folder.svg
    │       │       │   │   │   ├───folder-open.svg
    │       │       │   │   │   ├───font-awesome-logo-full.svg
    │       │       │   │   │   ├───frown.svg
    │       │       │   │   │   ├───frown-open.svg
    │       │       │   │   │   ├───futbol.svg
    │       │       │   │   │   ├───gem.svg
    │       │       │   │   │   ├───grin.svg
    │       │       │   │   │   ├───grin-alt.svg
    │       │       │   │   │   ├───grin-beam.svg
    │       │       │   │   │   ├───grin-beam-sweat.svg
    │       │       │   │   │   ├───grin-hearts.svg
    │       │       │   │   │   ├───grin-squint.svg
    │       │       │   │   │   ├───grin-squint-tears.svg
    │       │       │   │   │   ├───grin-stars.svg
    │       │       │   │   │   ├───grin-tears.svg
    │       │       │   │   │   ├───grin-tongue.svg
    │       │       │   │   │   ├───grin-tongue-squint.svg
    │       │       │   │   │   ├───grin-tongue-wink.svg
    │       │       │   │   │   ├───grin-wink.svg
    │       │       │   │   │   ├───grimace.svg
    │       │       │   │   │   ├───hand-lizard.svg
    │       │       │   │   │   ├───hand-paper.svg
    │       │       │   │   │   ├───hand-peace.svg
    │       │       │   │   │   ├───hand-point-down.svg
    │       │       │   │   │   ├───hand-point-left.svg
    │       │       │   │   │   ├───hand-point-right.svg
    │       │       │   │   │   ├───hand-point-up.svg
    │       │       │   │   │   ├───hand-pointer.svg
    │       │       │   │   │   ├───hand-rock.svg
    │       │       │   │   │   ├───hand-scissors.svg
    │       │       │   │   │   ├───hand-spock.svg
    │       │       │   │   │   ├───handshake.svg
    │       │       │   │   │   ├───hdd.svg
    │       │       │   │   │   ├───heart.svg
    │       │       │   │   │   ├───hospital.svg
    │       │       │   │   │   ├───hourglass.svg
    │       │       │   │   │   ├───id-badge.svg
    │       │       │   │   │   ├───id-card.svg
    │       │       │   │   │   ├───image.svg
    │       │       │   │   │   ├───images.svg
    │       │       │   │   │   ├───keyboard.svg
    │       │       │   │   │   ├───kiss.svg
    │       │       │   │   │   ├───kiss-beam.svg
    │       │       │   │   │   ├───kiss-wink-heart.svg
    │       │       │   │   │   ├───laugh.svg
    │       │       │   │   │   ├───laugh-beam.svg
    │       │       │   │   │   ├───laugh-squint.svg
    │       │       │   │   │   ├───laugh-wink.svg
    │       │       │   │   │   ├───lemon.svg
    │       │       │   │   │   ├───life-ring.svg
    │       │       │   │   │   ├───lightbulb.svg
    │       │       │   │   │   ├───list-alt.svg
    │       │       │   │   │   ├───map.svg
    │       │       │   │   │   ├───meh.svg
    │       │       │   │   │   ├───meh-blank.svg
    │       │       │   │   │   ├───meh-rolling-eyes.svg
    │       │       │   │   │   ├───minus-square.svg
    │       │       │   │   │   ├───money-bill-alt.svg
    │       │       │   │   │   ├───moon.svg
    │       │       │   │   │   ├───newspaper.svg
    │       │       │   │   │   ├───object-group.svg
    │       │       │   │   │   ├───object-ungroup.svg
    │       │       │   │   │   ├───paper-plane.svg
    │       │       │   │   │   ├───pause-circle.svg
    │       │       │   │   │   ├───play-circle.svg
    │       │       │   │   │   ├───plus-square.svg
    │       │       │   │   │   ├───question-circle.svg
    │       │       │   │   │   ├───registered.svg
    │       │       │   │   │   ├───sad-cry.svg
    │       │       │   │   │   ├───sad-tear.svg
    │       │       │   │   │   ├───save.svg
    │       │       │   │   │   ├───share-square.svg
    │       │       │   │   │   ├───snowflake.svg
    │       │       │   │   │   ├───smile.svg
    │       │       │   │   │   ├───smile-beam.svg
    │       │       │   │   │   ├───smile-wink.svg
    │       │       │   │   │   ├───square.svg
    │       │       │   │   │   ├───star.svg
    │       │       │   │   │   ├───star-half.svg
    │       │       │   │   │   ├───sticky-note.svg
    │       │       │   │   │   ├───stop-circle.svg
    │       │       │   │   │   ├───sun.svg
    │       │       │   │   │   ├───surprise.svg
    │       │       │   │   │   ├───thumbs-down.svg
    │       │       │   │   │   ├───thumbs-up.svg
    │       │       │   │   │   ├───times-circle.svg
    │       │       │   │   │   ├───tired.svg
    │       │       │   │   │   ├───trash-alt.svg
    │       │       │   │   │   ├───user.svg
    │       │       │   │   │   ├───user-circle.svg
    │       │       │   │   │   ├───window-close.svg
    │       │       │   │   │   ├───window-maximize.svg
    │       │       │   │   │   ├───window-minimize.svg
    │       │       │   │   │   └───window-restore.svg
    │       │       │   │   └───solid/
    │       │       │   │       ├───ad.svg
    │       │       │   │       ├───address-book.svg
    │       │       │   │       ├───address-card.svg
    │       │       │   │       ├───adjust.svg
    │       │       │   │       ├───air-freshener.svg
    │       │       │   │       ├───align-center.svg
    │       │       │   │       ├───align-justify.svg
    │       │       │   │       ├───align-left.svg
    │       │       │   │       ├───align-right.svg
    │       │       │   │       ├───allergies.svg
    │       │       │   │       ├───ambulance.svg
    │       │       │   │       ├───american-sign-language-interpreting.svg
    │       │       │   │       ├───anchor.svg
    │       │       │   │       ├───angle-double-down.svg
    │       │       │   │       ├───angle-double-left.svg
    │       │       │   │       ├───angle-double-right.svg
    │       │       │   │       ├───angle-double-up.svg
    │       │       │   │       ├───angle-down.svg
    │       │       │   │       ├───angle-left.svg
    │       │       │   │       ├───angle-right.svg
    │       │       │   │       ├───angle-up.svg
    │       │       │   │       ├───angry.svg
    │       │       │   │       ├───ankh.svg
    │       │       │   │       ├───apple-alt.svg
    │       │       │   │       ├───archive.svg
    │       │       │   │       ├───archway.svg
    │       │       │   │       ├───arrow-alt-circle-down.svg
    │       │       │   │       ├───arrow-alt-circle-left.svg
    │       │       │   │       ├───arrow-alt-circle-right.svg
    │       │       │   │       ├───arrow-alt-circle-up.svg
    │       │       │   │       ├───arrow-circle-down.svg
    │       │       │   │       ├───arrow-circle-left.svg
    │       │       │   │       ├───arrow-circle-right.svg
    │       │       │   │       ├───arrow-circle-up.svg
    │       │       │   │       ├───arrow-down.svg
    │       │       │   │       ├───arrow-left.svg
    │       │       │   │       ├───arrow-right.svg
    │       │       │   │       ├───arrow-up.svg
    │       │       │   │       ├───arrows-alt.svg
    │       │       │   │       ├───arrows-alt-h.svg
    │       │       │   │       ├───arrows-alt-v.svg
    │       │       │   │       ├───assistive-listening-systems.svg
    │       │       │   │       ├───asterisk.svg
    │       │       │   │       ├───at.svg
    │       │       │   │       ├───atlas.svg
    │       │       │   │       ├───atom.svg
    │       │       │   │       ├───audio-description.svg
    │       │       │   │       ├───award.svg
    │       │       │   │       ├───baby.svg
    │       │       │   │       ├───baby-carriage.svg
    │       │       │   │       ├───backspace.svg
    │       │       │   │       ├───backward.svg
    │       │       │   │       ├───bacon.svg
    │       │       │   │       ├───bacteria.svg
    │       │       │   │       ├───bacterium.svg
    │       │       │   │       ├───bahai.svg
    │       │       │   │       ├───balance-scale.svg
    │       │       │   │       ├───balance-scale-left.svg
    │       │       │   │       ├───balance-scale-right.svg
    │       │       │   │       ├───ban.svg
    │       │       │   │       ├───band-aid.svg
    │       │       │   │       ├───barcode.svg
    │       │       │   │       ├───bars.svg
    │       │       │   │       ├───baseball-ball.svg
    │       │       │   │       ├───basketball-ball.svg
    │       │       │   │       ├───bath.svg
    │       │       │   │       ├───battery-empty.svg
    │       │       │   │       ├───battery-full.svg
    │       │       │   │       ├───battery-half.svg
    │       │       │   │       ├───battery-quarter.svg
    │       │       │   │       ├───battery-three-quarters.svg
    │       │       │   │       ├───bed.svg
    │       │       │   │       ├───beer.svg
    │       │       │   │       ├───bell.svg
    │       │       │   │       ├───bell-slash.svg
    │       │       │   │       ├───bezier-curve.svg
    │       │       │   │       ├───bible.svg
    │       │       │   │       ├───bicycle.svg
    │       │       │   │       ├───biking.svg
    │       │       │   │       ├───binoculars.svg
    │       │       │   │       ├───biohazard.svg
    │       │       │   │       ├───birthday-cake.svg
    │       │       │   │       ├───blender.svg
    │       │       │   │       ├───blender-phone.svg
    │       │       │   │       ├───blind.svg
    │       │       │   │       ├───blog.svg
    │       │       │   │       ├───bold.svg
    │       │       │   │       ├───bolt.svg
    │       │       │   │       ├───bomb.svg
    │       │       │   │       ├───bone.svg
    │       │       │   │       ├───bong.svg
    │       │       │   │       ├───book.svg
    │       │       │   │       ├───book-dead.svg
    │       │       │   │       ├───book-medical.svg
    │       │       │   │       ├───book-open.svg
    │       │       │   │       ├───book-reader.svg
    │       │       │   │       ├───bookmark.svg
    │       │       │   │       ├───border-all.svg
    │       │       │   │       ├───border-none.svg
    │       │       │   │       ├───border-style.svg
    │       │       │   │       ├───bowling-ball.svg
    │       │       │   │       ├───box.svg
    │       │       │   │       ├───box-open.svg
    │       │       │   │       ├───box-tissue.svg
    │       │       │   │       ├───boxes.svg
    │       │       │   │       ├───braille.svg
    │       │       │   │       ├───brain.svg
    │       │       │   │       ├───bread-slice.svg
    │       │       │   │       ├───briefcase.svg
    │       │       │   │       ├───briefcase-medical.svg
    │       │       │   │       ├───broadcast-tower.svg
    │       │       │   │       ├───broom.svg
    │       │       │   │       ├───brush.svg
    │       │       │   │       ├───bug.svg
    │       │       │   │       ├───building.svg
    │       │       │   │       ├───bullhorn.svg
    │       │       │   │       ├───bullseye.svg
    │       │       │   │       ├───burn.svg
    │       │       │   │       ├───bus.svg
    │       │       │   │       ├───bus-alt.svg
    │       │       │   │       ├───business-time.svg
    │       │       │   │       ├───calculator.svg
    │       │       │   │       ├───calendar.svg
    │       │       │   │       ├───calendar-alt.svg
    │       │       │   │       ├───calendar-check.svg
    │       │       │   │       ├───calendar-day.svg
    │       │       │   │       ├───calendar-minus.svg
    │       │       │   │       ├───calendar-plus.svg
    │       │       │   │       ├───calendar-times.svg
    │       │       │   │       ├───calendar-week.svg
    │       │       │   │       ├───camera.svg
    │       │       │   │       ├───camera-retro.svg
    │       │       │   │       ├───campground.svg
    │       │       │   │       ├───candy-cane.svg
    │       │       │   │       ├───cannabis.svg
    │       │       │   │       ├───capsules.svg
    │       │       │   │       ├───car.svg
    │       │       │   │       ├───car-alt.svg
    │       │       │   │       ├───car-battery.svg
    │       │       │   │       ├───car-crash.svg
    │       │       │   │       ├───car-side.svg
    │       │       │   │       ├───caravan.svg
    │       │       │   │       ├───caret-down.svg
    │       │       │   │       ├───caret-left.svg
    │       │       │   │       ├───caret-right.svg
    │       │       │   │       ├───caret-square-down.svg
    │       │       │   │       ├───caret-square-left.svg
    │       │       │   │       ├───caret-square-right.svg
    │       │       │   │       ├───caret-square-up.svg
    │       │       │   │       ├───caret-up.svg
    │       │       │   │       ├───carrot.svg
    │       │       │   │       ├───cart-arrow-down.svg
    │       │       │   │       ├───cart-plus.svg
    │       │       │   │       ├───cash-register.svg
    │       │       │   │       ├───cat.svg
    │       │       │   │       ├───certificate.svg
    │       │       │   │       ├───chair.svg
    │       │       │   │       ├───chalkboard.svg
    │       │       │   │       ├───chalkboard-teacher.svg
    │       │       │   │       ├───charging-station.svg
    │       │       │   │       ├───chart-area.svg
    │       │       │   │       ├───chart-bar.svg
    │       │       │   │       ├───chart-line.svg
    │       │       │   │       ├───chart-pie.svg
    │       │       │   │       ├───check.svg
    │       │       │   │       ├───check-circle.svg
    │       │       │   │       ├───check-double.svg
    │       │       │   │       ├───check-square.svg
    │       │       │   │       ├───cheese.svg
    │       │       │   │       ├───chess.svg
    │       │       │   │       ├───chess-bishop.svg
    │       │       │   │       ├───chess-board.svg
    │       │       │   │       ├───chess-king.svg
    │       │       │   │       ├───chess-knight.svg
    │       │       │   │       ├───chess-pawn.svg
    │       │       │   │       ├───chess-queen.svg
    │       │       │   │       ├───chess-rook.svg
    │       │       │   │       ├───chevron-circle-down.svg
    │       │       │   │       ├───chevron-circle-left.svg
    │       │       │   │       ├───chevron-circle-right.svg
    │       │       │   │       ├───chevron-circle-up.svg
    │       │       │   │       ├───chevron-down.svg
    │       │       │   │       ├───chevron-left.svg
    │       │       │   │       ├───chevron-right.svg
    │       │       │   │       ├───chevron-up.svg
    │       │       │   │       ├───child.svg
    │       │       │   │       ├───church.svg
    │       │       │   │       ├───circle.svg
    │       │       │   │       ├───circle-notch.svg
    │       │       │   │       ├───city.svg
    │       │       │   │       ├───clinic-medical.svg
    │       │       │   │       ├───clipboard.svg
    │       │       │   │       ├───clipboard-check.svg
    │       │       │   │       ├───clipboard-list.svg
    │       │       │   │       ├───clock.svg
    │       │       │   │       ├───clone.svg
    │       │       │   │       ├───closed-captioning.svg
    │       │       │   │       ├───cloud.svg
    │       │       │   │       ├───cloud-download-alt.svg
    │       │       │   │       ├───cloud-meatball.svg
    │       │       │   │       ├───cloud-moon.svg
    │       │       │   │       ├───cloud-moon-rain.svg
    │       │       │   │       ├───cloud-rain.svg
    │       │       │   │       ├───cloud-showers-heavy.svg
    │       │       │   │       ├───cloud-sun.svg
    │       │       │   │       ├───cloud-sun-rain.svg
    │       │       │   │       ├───cloud-upload-alt.svg
    │       │       │   │       ├───cocktail.svg
    │       │       │   │       ├───code.svg
    │       │       │   │       ├───code-branch.svg
    │       │       │   │       ├───coffee.svg
    │       │       │   │       ├───cog.svg
    │       │       │   │       ├───cogs.svg
    │       │       │   │       ├───coins.svg
    │       │       │   │       ├───columns.svg
    │       │       │   │       ├───comment.svg
    │       │       │   │       ├───comment-alt.svg
    │       │       │   │       ├───comment-dollar.svg
    │       │       │   │       ├───comment-dots.svg
    │       │       │   │       ├───comment-medical.svg
    │       │       │   │       ├───comment-slash.svg
    │       │       │   │       ├───comments.svg
    │       │       │   │       ├───comments-dollar.svg
    │       │       │   │       ├───compact-disc.svg
    │       │       │   │       ├───compass.svg
    │       │       │   │       ├───compress.svg
    │       │       │   │       ├───compress-alt.svg
    │       │       │   │       ├───compress-arrows-alt.svg
    │       │       │   │       ├───concierge-bell.svg
    │       │       │   │       ├───cookie.svg
    │       │       │   │       ├───cookie-bite.svg
    │       │       │   │       ├───copy.svg
    │       │       │   │       ├───copyright.svg
    │       │       │   │       ├───couch.svg
    │       │       │   │       ├───credit-card.svg
    │       │       │   │       ├───crop.svg
    │       │       │   │       ├───crop-alt.svg
    │       │       │   │       ├───cross.svg
    │       │       │   │       ├───crosshairs.svg
    │       │       │   │       ├───crow.svg
    │       │       │   │       ├───crown.svg
    │       │       │   │       ├───crutch.svg
    │       │       │   │       ├───cube.svg
    │       │       │   │       ├───cubes.svg
    │       │       │   │       ├───cut.svg
    │       │       │   │       ├───database.svg
    │       │       │   │       ├───deaf.svg
    │       │       │   │       ├───democrat.svg
    │       │       │   │       ├───desktop.svg
    │       │       │   │       ├───dharmachakra.svg
    │       │       │   │       ├───diagnoses.svg
    │       │       │   │       ├───dice.svg
    │       │       │   │       ├───dice-d20.svg
    │       │       │   │       ├───dice-d6.svg
    │       │       │   │       ├───dice-five.svg
    │       │       │   │       ├───dice-four.svg
    │       │       │   │       ├───dice-one.svg
    │       │       │   │       ├───dice-six.svg
    │       │       │   │       ├───dice-three.svg
    │       │       │   │       ├───dice-two.svg
    │       │       │   │       ├───digital-tachograph.svg
    │       │       │   │       ├───directions.svg
    │       │       │   │       ├───disease.svg
    │       │       │   │       ├───divide.svg
    │       │       │   │       ├───dizzy.svg
    │       │       │   │       ├───dna.svg
    │       │       │   │       ├───dog.svg
    │       │       │   │       ├───dollar-sign.svg
    │       │       │   │       ├───dolly.svg
    │       │       │   │       ├───dolly-flatbed.svg
    │       │       │   │       ├───donate.svg
    │       │       │   │       ├───door-closed.svg
    │       │       │   │       ├───door-open.svg
    │       │       │   │       ├───dot-circle.svg
    │       │       │   │       ├───dove.svg
    │       │       │   │       ├───download.svg
    │       │       │   │       ├───drafting-compass.svg
    │       │       │   │       ├───dragon.svg
    │       │       │   │       ├───draw-polygon.svg
    │       │       │   │       ├───drum.svg
    │       │       │   │       ├───drum-steelpan.svg
    │       │       │   │       ├───drumstick-bite.svg
    │       │       │   │       ├───dumbbell.svg
    │       │       │   │       ├───dumpster.svg
    │       │       │   │       ├───dumpster-fire.svg
    │       │       │   │       ├───dungeon.svg
    │       │       │   │       ├───edit.svg
    │       │       │   │       ├───egg.svg
    │       │       │   │       ├───eject.svg
    │       │       │   │       ├───ellipsis-h.svg
    │       │       │   │       ├───ellipsis-v.svg
    │       │       │   │       ├───envelope.svg
    │       │       │   │       ├───envelope-open.svg
    │       │       │   │       ├───envelope-open-text.svg
    │       │       │   │       ├───envelope-square.svg
    │       │       │   │       ├───equals.svg
    │       │       │   │       ├───eraser.svg
    │       │       │   │       ├───ethernet.svg
    │       │       │   │       ├───euro-sign.svg
    │       │       │   │       ├───exchange-alt.svg
    │       │       │   │       ├───exclamation.svg
    │       │       │   │       ├───exclamation-circle.svg
    │       │       │   │       ├───exclamation-triangle.svg
    │       │       │   │       ├───expand.svg
    │       │       │   │       ├───expand-alt.svg
    │       │       │   │       ├───expand-arrows-alt.svg
    │       │       │   │       ├───external-link-alt.svg
    │       │       │   │       ├───external-link-square-alt.svg
    │       │       │   │       ├───eye.svg
    │       │       │   │       ├───eye-dropper.svg
    │       │       │   │       ├───eye-slash.svg
    │       │       │   │       ├───fan.svg
    │       │       │   │       ├───fast-backward.svg
    │       │       │   │       ├───fast-forward.svg
    │       │       │   │       ├───faucet.svg
    │       │       │   │       ├───fax.svg
    │       │       │   │       ├───feather.svg
    │       │       │   │       ├───feather-alt.svg
    │       │       │   │       ├───female.svg
    │       │       │   │       ├───fighter-jet.svg
    │       │       │   │       ├───file.svg
    │       │       │   │       ├───file-alt.svg
    │       │       │   │       ├───file-archive.svg
    │       │       │   │       ├───file-audio.svg
    │       │       │   │       ├───file-code.svg
    │       │       │   │       ├───file-contract.svg
    │       │       │   │       ├───file-csv.svg
    │       │       │   │       ├───file-download.svg
    │       │       │   │       ├───file-excel.svg
    │       │       │   │       ├───file-export.svg
    │       │       │   │       ├───file-image.svg
    │       │       │   │       ├───file-import.svg
    │       │       │   │       ├───file-invoice.svg
    │       │       │   │       ├───file-invoice-dollar.svg
    │       │       │   │       ├───file-medical.svg
    │       │       │   │       ├───file-medical-alt.svg
    │       │       │   │       ├───file-pdf.svg
    │       │       │   │       ├───file-powerpoint.svg
    │       │       │   │       ├───file-prescription.svg
    │       │       │   │       ├───file-signature.svg
    │       │       │   │       ├───file-upload.svg
    │       │       │   │       ├───file-video.svg
    │       │       │   │       ├───file-word.svg
    │       │       │   │       ├───fill.svg
    │       │       │   │       ├───fill-drip.svg
    │       │       │   │       ├───film.svg
    │       │       │   │       ├───filter.svg
    │       │       │   │       ├───fingerprint.svg
    │       │       │   │       ├───fire.svg
    │       │       │   │       ├───fire-alt.svg
    │       │       │   │       ├───fire-extinguisher.svg
    │       │       │   │       ├───first-aid.svg
    │       │       │   │       ├───fish.svg
    │       │       │   │       ├───fist-raised.svg
    │       │       │   │       ├───flag.svg
    │       │       │   │       ├───flag-checkered.svg
    │       │       │   │       ├───flag-usa.svg
    │       │       │   │       ├───flask.svg
    │       │       │   │       ├───flushed.svg
    │       │       │   │       ├───folder.svg
    │       │       │   │       ├───folder-minus.svg
    │       │       │   │       ├───folder-open.svg
    │       │       │   │       ├───folder-plus.svg
    │       │       │   │       ├───font.svg
    │       │       │   │       ├───font-awesome-logo-full.svg
    │       │       │   │       ├───football-ball.svg
    │       │       │   │       ├───forward.svg
    │       │       │   │       ├───frog.svg
    │       │       │   │       ├───frown.svg
    │       │       │   │       ├───frown-open.svg
    │       │       │   │       ├───funnel-dollar.svg
    │       │       │   │       ├───futbol.svg
    │       │       │   │       ├───gamepad.svg
    │       │       │   │       ├───gas-pump.svg
    │       │       │   │       ├───gavel.svg
    │       │       │   │       ├───gem.svg
    │       │       │   │       ├───genderless.svg
    │       │       │   │       ├───ghost.svg
    │       │       │   │       ├───gift.svg
    │       │       │   │       ├───gifts.svg
    │       │       │   │       ├───glass-cheers.svg
    │       │       │   │       ├───glass-martini.svg
    │       │       │   │       ├───glass-martini-alt.svg
    │       │       │   │       ├───glass-whiskey.svg
    │       │       │   │       ├───glasses.svg
    │       │       │   │       ├───globe.svg
    │       │       │   │       ├───globe-africa.svg
    │       │       │   │       ├───globe-americas.svg
           │       │   │       ├───globe-asia.svg
    │       │       │   │       ├───globe-europe.svg
    │       │       │   │       ├───golf-ball.svg
    │       │       │   │       ├───gopuram.svg
    │       │       │   │       ├───graduation-cap.svg
    │       │       │   │       ├───greater-than.svg
    │       │       │   │       ├───greater-than-equal.svg
    │       │       │   │       ├───grimace.svg
    │       │       │   │       ├───grin.svg
    │       │       │   │       ├───grin-alt.svg
    │       │       │   │       ├───grin-beam.svg
    │       │       │   │       ├───grin-beam-sweat.svg
    │       │       │   │       ├───grin-hearts.svg
    │       │       │   │       ├───grin-squint.svg
    │       │       │   │       ├───grin-squint-tears.svg
    │       │       │   │       ├───grin-stars.svg
    │       │       │   │       ├───grin-tears.svg
    │       │       │   │       ├───grin-tongue.svg
    │       │       │   │       ├───grin-tongue-squint.svg
    │       │       │   │       ├───grin-tongue-wink.svg
    │       │       │   │       ├───grin-wink.svg
    │       │       │   │       ├───grip-horizontal.svg
    │       │       │   │       ├───grip-lines.svg
    │       │       │   │       ├───grip-lines-vertical.svg
    │       │       │   │       ├───grip-vertical.svg
    │       │       │   │       ├───guitar.svg
    │       │       │   │       ├───h-square.svg
    │       │       │   │       ├───hamburger.svg
    │       │       │   │       ├───hammer.svg
    │       │       │   │       ├───hamsa.svg
    │       │       │   │       ├───hand-holding.svg
    │       │       │   │       ├───hand-holding-heart.svg
    │       │       │   │       ├───hand-holding-medical.svg
    │       │       │   │       ├───hand-holding-usd.svg
    │       │       │   │       ├───hand-holding-water.svg
    │       │       │   │       ├───hand-lizard.svg
    │       │       │   │       ├───hand-middle-finger.svg
    │       │       │   │       ├───hand-paper.svg
    │       │       │   │       ├───hand-peace.svg
    │       │       │   │       ├───hand-point-down.svg
    │       │       │   │       ├───hand-point-left.svg
    │       │       │   │       ├───hand-point-right.svg
    │       │       │   │       ├───hand-point-up.svg
    │       │       │   │       ├───hand-pointer.svg
    │       │       │   │       ├───hand-rock.svg
    │       │       │   │       ├───hand-scissors.svg
    │       │       │   │       ├───hand-sparkles.svg
    │       │       │   │       ├───hand-spock.svg
    │       │       │   │       ├───hands.svg
    │       │       │   │       ├───hands-helping.svg
    │       │       │   │       ├───hands-wash.svg
    │       │       │   │       ├───handshake.svg
    │       │       │   │       ├───handshake-alt-slash.svg
    │       │       │   │       ├───handshake-slash.svg
    │       │       │   │       ├───hanukiah.svg
    │       │       │   │       ├───hard-hat.svg
    │       │       │   │       ├───hashtag.svg
    │       │       │   │       ├───hat-cowboy.svg
    │       │       │   │       ├───hat-cowboy-side.svg
    │       │       │   │       ├───hat-wizard.svg
    │       │       │   │       ├───hdd.svg
    │       │       │   │       ├───head-side-cough.svg
    │       │       │   │       ├───head-side-cough-slash.svg
    │       │       │   │       ├───head-side-mask.svg
    │       │       │   │       ├───head-side-virus.svg
    │       │       │   │       ├───heading.svg
    │       │       │   │       ├───headphones.svg
    │       │       │   │       ├───headphones-alt.svg
    │       │       │   │       ├───headset.svg
    │       │       │   │       ├───heart.svg
    │       │       │   │       ├───heart-broken.svg
    │       │       │   │       ├───heartbeat.svg
    │       │       │   │       ├───helicopter.svg
    │       │       │   │       ├───highlighter.svg
    │       │       │   │       ├───hiking.svg
    │       │       │   │       ├───hippo.svg
    │       │       │   │       ├───history.svg
    │       │       │   │       ├───hockey-puck.svg
    │       │       │   │       ├───holly-berry.svg
    │       │       │   │       ├───home.svg
    │       │       │   │       ├───horse.svg
    │       │       │   │       ├───horse-head.svg
    │       │       │   │       ├───hospital.svg
    │       │       │   │       ├───hospital-alt.svg
    │       │       │   │       ├───hospital-symbol.svg
    │       │       │   │       ├───hospital-user.svg
    │       │       │   │       ├───hot-tub.svg
    │       │       │   │       ├───hotdog.svg
    │       │       │   │       ├───hotel.svg
    │       │       │   │       ├───hourglass.svg
    │       │       │   │       ├───hourglass-end.svg
    │       │       │   │       ├───hourglass-half.svg
    │       │       │   │       ├───hourglass-start.svg
    │       │       │   │       ├───house-damage.svg
    │       │       │   │       ├───house-user.svg
    │       │       │   │       ├───hryvnia.svg
    │       │       │   │       ├───i-cursor.svg
    │       │       │   │       ├───ice-cream.svg
    │       │       │   │       ├───icicles.svg
    │       │       │   │       ├───icons.svg
    │       │       │   │       ├───id-badge.svg
    │       │       │   │       ├───id-card.svg
    │       │       │   │       ├───id-card-alt.svg
    │       │       │   │       ├───igloo.svg
    │       │       │   │       ├───image.svg
    │       │       │   │       ├───images.svg
    │       │       │   │       ├───inbox.svg
    │       │       │   │       ├───indent.svg
    │       │       │   │       ├───industry.svg
    │       │       │   │       ├───infinity.svg
    │       │       │   │       ├───info.svg
    │       │       │   │       ├───info-circle.svg
    │       │       │   │       ├───italic.svg
    │       │       │   │       ├───jedi.svg
    │       │       │   │       ├───joint.svg
    │       │       │   │       ├───journal-whills.svg
    │       │       │   │       ├───kaaba.svg
    │       │       │   │       ├───key.svg
    │       │       │   │       ├───keyboard.svg
    │       │       │   │       ├───khanda.svg
    │       │       │   │       ├───kiss.svg
    │       │       │   │       ├───kiss-beam.svg
    │       │       │   │       ├───kiss-wink-heart.svg
    │       │       │   │       ├───kiwi-bird.svg
    │       │       │   │       ├───language.svg
    │       │       │   │       ├───laptop.svg
    │       │       │   │       ├───laptop-code.svg
    │       │       │   │       ├───laptop-house.svg
    │       │       │   │       ├───laptop-medical.svg
    │       │       │   │       ├───laugh.svg
    │       │       │   │       ├───laugh-beam.svg
    │       │       │   │       ├───laugh-squint.svg
    │       │       │   │       ├───laugh-wink.svg
    │       │       │   │       ├───layer-group.svg
    │       │       │   │       ├───leaf.svg
    │       │       │   │       ├───lemon.svg
    │       │       │   │       ├───less-than.svg
    │       │       │   │       ├───less-than-equal.svg
    │       │       │   │       ├───level-down-alt.svg
    │       │       │   │       ├───level-up-alt.svg
    │       │       │   │       ├───life-ring.svg
    │       │       │   │       ├───lightbulb.svg
    │       │       │   │       ├───link.svg
    │       │       │   │       ├───lira-sign.svg
    │       │       │   │       ├───list.svg
    │       │       │   │       ├───list-alt.svg
    │       │       │   │       ├───list-ol.svg
    │       │       │   │       ├───list-ul.svg
    │       │       │   │       ├───location-arrow.svg
    │       │       │   │       ├───lock.svg
    │       │       │   │       ├───lock-open.svg
    │       │       │   │       ├───long-arrow-alt-down.svg
    │       │       │   │       ├───long-arrow-alt-left.svg
    │       │       │   │       ├───long-arrow-alt-right.svg
    │       │       │   │       ├───long-arrow-alt-up.svg
    │       │       │   │       ├───low-vision.svg
    │       │       │   │       ├───luggage-cart.svg
    │       │       │   │       ├───lungs.svg
    │       │       │   │       ├───lungs-virus.svg
    │       │       │   │       ├───magic.svg
    │       │       │   │       ├───magnet.svg
    │       │       │   │       ├───mail-bulk.svg
    │       │       │   │       ├───male.svg
    │       │       │   │       ├───map.svg
    │       │       │   │       ├───map-marked.svg
    │       │       │   │       ├───map-marked-alt.svg
    │       │       │   │       ├───map-marker.svg
    │       │       │   │       ├───map-marker-alt.svg
    │       │       │   │       ├───map-pin.svg
    │       │       │   │       ├───map-signs.svg
    │       │       │   │       ├───marker.svg
    │       │       │   │       ├───mars.svg
    │       │       │   │       ├───mars-double.svg
    │       │       │   │       ├───mars-stroke.svg
    │       │       │   │       ├───mars-stroke-h.svg
    │       │       │   │       ├───mars-stroke-v.svg
    │       │       │   │       ├───mask.svg
    │       │       │   │       ├───medal.svg
    │       │       │   │       ├───medkit.svg
    │       │       │   │       ├───meh.svg
    │       │       │   │       ├───meh-blank.svg
    │       │       │   │       ├───meh-rolling-eyes.svg
    │       │       │   │       ├───menorah.svg
    │       │       │   │       ├───mercury.svg
    │       │       │   │       ├───meteor.svg
    │       │       │   │       ├───microchip.svg
    │       │       │   │       ├───microphone.svg
    │       │       │   │       ├───microphone-alt.svg
    │       │       │   │       ├───microphone-alt-slash.svg
    │       │       │   │       ├───microphone-slash.svg
    │       │       │   │       ├───microscope.svg
    │       │       │   │       ├───minus.svg
    │       │       │   │       ├───minus-circle.svg
    │       │       │   │       ├───minus-square.svg
    │       │       │   │       ├───mitten.svg
    │       │       │   │       ├───mobile.svg
    │       │       │   │       ├───mobile-alt.svg
    │       │       │   │       ├───money-bill.svg
    │       │       │   │       ├───money-bill-alt.svg
    │       │       │   │       ├───money-bill-wave.svg
    │       │       │   │       ├───money-bill-wave-alt.svg
    │       │       │   │       ├───money-check.svg
    │       │       │   │       ├───money-check-alt.svg
    │       │       │   │       ├───monument.svg
    │       │       │   │       ├───moon.svg
    │       │       │   │       ├───mortar-pestle.svg
    │       │       │   │       ├───mosque.svg
    │       │       │   │       ├───motorcycle.svg
    │       │       │   │       ├───mountain.svg
    │       │       │   │       ├───mouse.svg
    │       │       │   │       ├───mouse-pointer.svg
    │       │       │   │       ├───mug-hot.svg
    │       │       │   │       ├───music.svg
    │       │       │   │       ├───network-wired.svg
    │       │       │   │       ├───neuter.svg
    │       │       │   │       ├───newspaper.svg
    │       │       │   │       ├───not-equal.svg
    │       │       │   │       ├───notes-medical.svg
    │       │       │   │       ├───object-group.svg
    │       │       │   │       ├───object-ungroup.svg
    │       │       │   │       ├───oil-can.svg
    │       │       │   │       ├───om.svg
    │       │       │   │       ├───otter.svg
    │       │       │   │       ├───outdent.svg
    │       │       │   │       ├───pager.svg
    │       │       │   │       ├───paint-brush.svg
    │       │       │   │       ├───paint-roller.svg
    │       │       │   │       ├───palette.svg
    │       │       │   │       ├───pallet.svg
    │       │       │   │       ├───paper-plane.svg
    │       │       │   │       ├───paperclip.svg
    │       │       │   │       ├───parachute-box.svg
    │       │       │   │       ├───paragraph.svg
    │       │       │   │       ├───parking.svg
    │       │       │   │       ├───passport.svg
    │       │       │   │       ├───pastafarianism.svg
    │       │       │   │       ├───paste.svg
    │       │       │   │       ├───pause.svg
    │       │       │   │       ├───pause-circle.svg
    │       │       │   │       ├───paw.svg
    │       │       │   │       ├───peace.svg
    │       │       │   │       ├───pen.svg
    │       │       │   │       ├───pen-alt.svg
    │       │       │   │       ├───pen-fancy.svg
    │       │       │   │       ├───pen-nib.svg
    │       │       │   │       ├───pen-square.svg
    │       │       │   │       ├───pencil-alt.svg
    │       │       │   │       ├───pencil-ruler.svg
    │       │       │   │       ├───people-arrows.svg
    │       │       │   │       ├───people-carry.svg
    │       │       │   │       ├───pepper-hot.svg
    │       │       │   │       ├───percent.svg
    │       │       │   │       ├───percentage.svg
    │       │       │   │       ├───person-booth.svg
    │       │       │   │       ├───phone.svg
    │       │       │   │       ├───phone-alt.svg
    │       │       │   │       ├───phone-slash.svg
    │       │       │   │       ├───phone-square.svg
    │       │       │   │       ├───phone-square-alt.svg
    │       │       │   │       ├───phone-volume.svg
    │       │       │   │       ├───photo-video.svg
    │       │       │   │       ├───piggy-bank.svg
    │       │       │   │       ├───pills.svg
    │       │       │   │       ├───pizza-slice.svg
    │       │       │   │       ├───place-of-worship.svg
    │       │       │   │       ├───plane.svg
    │       │       │   │       ├───plane-arrival.svg
    │       │       │   │       ├───plane-departure.svg
    │       │       │   │       ├───plane-slash.svg
    │       │       │   │       ├───play.svg
    │       │       │   │       ├───play-circle.svg
    │       │       │   │       ├───plug.svg
    │       │       │   │       ├───plus.svg
    │       │       │   │       ├───plus-circle.svg
    │       │       │   │       ├───plus-square.svg
    │       │       │   │       ├───podcast.svg
    │       │       │   │       ├───poll.svg
    │       │       │   │       ├───poll-h.svg
    │       │       │   │       ├───poo.svg
    │       │       │   │       ├───poo-storm.svg
    │       │       │   │       ├───poop.svg
    │       │       │   │       ├───portrait.svg
    │       │       │   │       ├───pound-sign.svg
    │       │       │   │       ├───power-off.svg
    │       │       │   │       ├───pray.svg
    │       │       │   │       ├───praying-hands.svg
    │       │       │   │       ├───prescription.svg
    │       │       │   │       ├───prescription-bottle.svg
    │       │       │   │       ├───prescription-bottle-alt.svg
    │       │       │   │       ├───print.svg
    │       │       │   │       ├───procedures.svg
    │       │       │   │       ├───project-diagram.svg
    │       │       │   │       ├───pump-medical.svg
    │       │       │   │       ├───pump-soap.svg
    │       │       │   │       ├───puzzle-piece.svg
    │       │       │   │       ├───qrcode.svg
    │       │       │   │       ├───question.svg
    │       │       │   │       ├───question-circle.svg
    │       │       │   │       ├───quidditch.svg
    │       │       │   │       ├───quote-left.svg
    │       │       │   │       ├───quote-right.svg
    │       │       │   │       ├───quran.svg
    │       │       │   │       ├───radiation.svg
    │       │       │   │       ├───radiation-alt.svg
    │       │       │   │       ├───rainbow.svg
    │       │       │   │       ├───random.svg
    │       │       │   │       ├───receipt.svg
    │       │       │   │       ├───record-vinyl.svg
    │       │       │   │       ├───recycle.svg
    │       │       │   │       ├───redo.svg
    │       │       │   │       ├───redo-alt.svg
    │       │       │   │       ├───registered.svg
    │       │       │   │       ├───remove-format.svg
    │       │       │   │       ├───reply.svg
    │       │       │   │       ├───reply-all.svg
    │       │       │   │       ├───republican.svg
    │       │       │   │       ├───restroom.svg
    │       │       │   │       ├───retweet.svg
    │       │       │   │       ├───ribbon.svg
    │       │       │   │       ├───ring.svg
    │       │       │   │       ├───road.svg
    │       │       │   │       ├───robot.svg
    │       │       │   │       ├───rocket.svg
    │       │       │   │       ├───route.svg
    │       │       │   │       ├───rss.svg
    │       │       │   │       ├───rss-square.svg
    │       │       │   │       ├───ruble-sign.svg
    │       │       │   │       ├───ruler.svg
    │       │       │   │       ├───ruler-combined.svg
    │       │       │   │       ├───ruler-horizontal.svg
    │       │       │   │       ├───ruler-vertical.svg
    │       │       │   │       ├───running.svg
    │       │       │   │       ├───rupee-sign.svg
    │       │       │   │       ├───sad-cry.svg
    │       │       │   │       ├───sad-tear.svg
    │       │       │   │       ├───satellite.svg
    │       │       │   │       ├───satellite-dish.svg
    │       │       │   │       ├───save.svg
    │       │       │   │       ├───school.svg
    │       │       │   │       ├───screwdriver.svg
    │       │       │   │       ├───scroll.svg
    │       │       │   │       ├───sd-card.svg
    │       │       │   │       ├───search.svg
    │       │       │   │       ├───search-dollar.svg
    │       │       │   │       ├───search-location.svg
    │       │       │   │       ├───search-minus.svg
    │       │       │   │       ├───search-plus.svg
    │       │       │   │       ├───seedling.svg
    │       │       │   │       ├───server.svg
    │       │       │   │       ├───shapes.svg
    │       │       │   │       ├───share.svg
    │       │       │   │       ├───share-alt.svg
    │       │       │   │       ├───share-alt-square.svg
    │       │       │   │       ├───share-square.svg
    │       │       │   │       ├───shekel-sign.svg
    │       │       │   │       ├───shield-alt.svg
           │       │   │       ├───shield-virus.svg
    │       │       │   │       ├───ship.svg
    │       │       │   │       ├───shipping-fast.svg
    │       │       │   │       ├───shoe-prints.svg
    │       │       │   │       ├───shopping-bag.svg
    │       │       │   │       ├───shopping-basket.svg
    │       │       │   │       ├───shopping-cart.svg
    │       │       │   │       ├───shower.svg
    │       │       │   │       ├───shuttle-van.svg
    │       │       │   │       ├───sign.svg
    │       │       │   │       ├───sign-in-alt.svg
    │       │       │   │       ├───sign-language.svg
    │       │       │   │       ├───sign-out-alt.svg
    │       │       │   │       ├───signal.svg
    │       │       │   │       ├───signature.svg
    │       │       │   │       ├───sim-card.svg
    │       │       │   │       ├───sink.svg
    │       │       │   │       ├───sitemap.svg
    │       │       │   │       ├───skating.svg
    │       │       │   │       ├───skiing.svg
    │       │       │   │       ├───skiing-nordic.svg
    │       │       │   │       ├───skull.svg
    │       │       │   │       ├───skull-crossbones.svg
    │       │       │   │       ├───slash.svg
    │       │       │   │       ├───sleigh.svg
    │       │       │   │       ├───sliders-h.svg
    │       │       │   │       ├───smile.svg
    │       │       │   │       ├───smile-beam.svg
    │       │       │   │       ├───smile-wink.svg
    │       │       │   │       ├───smog.svg
    │       │       │   │       ├───smoking.svg
    │       │       │   │       ├───smoking-ban.svg
    │       │       │   │       ├───sms.svg
    │       │       │   │       ├───snowboarding.svg
    │       │       │   │       ├───snowflake.svg
    │       │       │   │       ├───snowman.svg
    │       │       │   │       ├───snowplow.svg
    │       │       │   │       ├───soap.svg
    │       │       │   │       ├───socks.svg
    │       │       │   │       ├───solar-panel.svg
    │       │       │   │       ├───sort.svg
    │       │       │   │       ├───sort-alpha-down.svg
    │       │       │   │       ├───sort-alpha-down-alt.svg
    │       │       │   │       ├───sort-alpha-up.svg
    │       │       │   │       ├───sort-alpha-up-alt.svg
    │       │       │   │       ├───sort-amount-down.svg
    │       │       │   │       ├───sort-amount-down-alt.svg
    │       │       │   │       ├───sort-amount-up.svg
    │       │       │   │       ├───sort-amount-up-alt.svg
    │       │       │   │       ├───sort-down.svg
    │       │       │   │       ├───sort-numeric-down.svg
    │       │       │   │       ├───sort-numeric-down-alt.svg
    │       │       │   │       ├───sort-numeric-up.svg
    │       │       │   │       ├───sort-numeric-up-alt.svg
    │       │       │   │       ├───sort-up.svg
    │       │       │   │       ├───spa.svg
    │       │       │   │       ├───space-shuttle.svg
    │       │       │   │       ├───spell-check.svg
    │       │       │   │       ├───spider.svg
    │       │       │   │       ├───spinner.svg
    │       │       │   │       ├───splotch.svg
    │       │       │   │       ├───spray-can.svg
    │       │       │   │       ├───square.svg
    │       │       │   │       ├───square-full.svg
    │       │       │   │       ├───square-root-alt.svg
    │       │       │   │       ├───stamp.svg
    │       │       │   │       ├───star.svg
    │       │       │   │       ├───star-and-crescent.svg
    │       │       │   │       ├───star-half.svg
    │       │       │   │       ├───star-half-alt.svg
    │       │       │   │       ├───star-of-david.svg
    │       │       │   │       ├───star-of-life.svg
    │       │       │   │       ├───step-backward.svg
    │       │       │   │       ├───step-forward.svg
    │       │       │   │       ├───stethoscope.svg
    │       │       │   │       ├───sticky-note.svg
    │       │       │   │       ├───stop.svg
    │       │       │   │       ├───stop-circle.svg
    │       │       │   │       ├───stopwatch.svg
    │       │       │   │       ├───stopwatch-20.svg
    │       │       │   │       ├───store.svg
    │       │       │   │       ├───store-alt.svg
    │       │       │   │       ├───store-alt-slash.svg
    │       │       │   │       ├───store-slash.svg
    │       │       │   │       ├───stream.svg
    │       │       │   │       ├───street-view.svg
    │       │       │   │       ├───strikethrough.svg
    │       │       │   │       ├───stroopwafel.svg
    │       │       │   │       ├───subscript.svg
    │       │       │   │       ├───subway.svg
    │       │       │   │       ├───suitcase.svg
    │       │       │   │       ├───suitcase-rolling.svg
    │       │       │   │       ├───sun.svg
    │       │       │   │       ├───superscript.svg
    │       │       │   │       ├───surprise.svg
    │       │       │   │       ├───swatchbook.svg
    │       │       │   │       ├───swimmer.svg
    │       │       │   │       ├───swimming-pool.svg
    │       │       │   │       ├───synagogue.svg
    │       │       │   │       ├───sync.svg
    │       │       │   │       ├───sync-alt.svg
    │       │       │   │       ├───syringe.svg
    │       │       │   │       ├───table.svg
    │       │       │   │       ├───table-tennis.svg
    │       │       │   │       ├───tablet.svg
    │       │       │   │       ├───tablet-alt.svg
    │       │       │   │       ├───tablets.svg
    │       │       │   │       ├───tachometer-alt.svg
    │       │       │   │       ├───tag.svg
    │       │       │   │       ├───tags.svg
    │       │       │   │       ├───tape.svg
    │       │       │   │       ├───tasks.svg
    │       │       │   │       ├───taxi.svg
    │       │       │   │       ├───teeth.svg
    │       │       │   │       ├───teeth-open.svg
    │       │       │   │       ├───temperature-high.svg
    │       │       │   │       ├───temperature-low.svg
    │       │       │   │       ├───tenge.svg
    │       │       │   │       ├───terminal.svg
    │       │       │   │       ├───text-height.svg
    │       │       │   │       ├───text-width.svg
    │       │       │   │       ├───th.svg
    │       │       │   │       ├───th-large.svg
    │       │       │   │       ├───th-list.svg
    │       │       │   │       ├───theater-masks.svg
    │       │       │   │       ├───thermometer.svg
    │       │       │   │       ├───thermometer-empty.svg
    │       │       │   │       ├───thermometer-full.svg
    │       │       │   │       ├───thermometer-half.svg
    │       │       │   │       ├───thermometer-quarter.svg
    │       │       │   │       ├───thermometer-three-quarters.svg
    │       │       │   │       ├───thumbs-down.svg
    │       │       │   │       ├───thumbs-up.svg
    │       │       │   │       ├───thumbtack.svg
    │       │       │   │       ├───ticket-alt.svg
    │       │       │   │       ├───times.svg
    │       │       │   │       ├───times-circle.svg
    │       │       │   │       ├───tint.svg
    │       │       │   │       ├───tint-slash.svg
    │       │       │   │       ├───tired.svg
    │       │       │   │       ├───toggle-off.svg
    │       │       │   │       ├───toggle-on.svg
    │       │       │   │       ├───toilet.svg
    │       │       │   │       ├───toilet-paper.svg
    │       │       │   │       ├───toilet-paper-slash.svg
    │       │       │   │       ├───toolbox.svg
    │       │       │   │       ├───tools.svg
    │       │       │   │       ├───tooth.svg
    │       │       │   │       ├───torah.svg
    │       │       │   │       ├───torii-gate.svg
    │       │       │   │       ├───tractor.svg
    │       │       │   │       ├───trademark.svg
    │       │       │   │       ├───traffic-light.svg
    │       │       │   │       ├───trailer.svg
    │       │       │   │       ├───train.svg
    │       │       │   │       ├───tram.svg
    │       │       │   │       ├───transgender.svg
    │       │       │   │       ├───transgender-alt.svg
    │       │       │   │       ├───trash.svg
    │       │       │   │       ├───trash-alt.svg
    │       │       │   │       ├───trash-restore.svg
    │       │       │   │       ├───trash-restore-alt.svg
    │       │       │   │       ├───tree.svg
    │       │       │   │       ├───trophy.svg
    │       │       │   │       ├───truck.svg
    │       │       │   │       ├───truck-loading.svg
    │       │       │   │       ├───truck-monster.svg
    │       │       │   │       ├───truck-moving.svg
    │       │       │   │       ├───truck-pickup.svg
    │       │       │   │       ├───tty.svg
    │       │       │   │       ├───tv.svg
    │       │       │   │       ├───umbrella.svg
    │       │       │   │       ├───umbrella-beach.svg
    │       │       │   │       ├───underline.svg
    │       │       │   │       ├───undo.svg
    │       │       │   │       ├───undo-alt.svg
    │       │       │   │       ├───universal-access.svg
    │       │       │   │       ├───university.svg
    │       │       │   │       ├───unlink.svg
    │       │       │   │       ├───unlock.svg
    │       │       │   │       ├───unlock-alt.svg
    │       │       │   │       ├───upload.svg
    │       │       │   │       ├───user.svg
    │       │       │   │       ├───user-alt.svg
    │       │       │   │       ├───user-alt-slash.svg
    │       │       │   │       ├───user-astronaut.svg
    │       │       │   │       ├───user-check.svg
    │       │       │   │       ├───user-circle.svg
    │       │       │   │       ├───user-clock.svg
    │       │       │   │       ├───user-cog.svg
    │       │       │   │       ├───user-edit.svg
    │       │       │   │       ├───user-friends.svg
    │       │       │   │       ├───user-graduate.svg
    │       │       │   │       ├───user-injured.svg
    │       │       │   │       ├───user-lock.svg
    │       │       │   │       ├───user-md.svg
    │       │       │   │       ├───user-minus.svg
    │       │       │   │       ├───user-ninja.svg
    │       │       │   │       ├───user-nurse.svg
    │       │       │   │       ├───user-plus.svg
    │       │       │   │       ├───user-secret.svg
    │       │       │   │       ├───user-shield.svg
    │       │       │   │       ├───user-slash.svg
    │       │       │   │       ├───user-tag.svg
    │       │       │   │       ├───user-tie.svg
    │       │       │   │       ├───user-times.svg
    │       │       │   │       ├───users.svg
    │       │       │   │       ├───users-cog.svg
    │       │       │   │       ├───users-slash.svg
    │       │       │   │       ├───utensil-spoon.svg
    │       │       │   │       ├───utensils.svg
    │       │       │   │       ├───venus.svg
    │       │       │   │       ├───venus-double.svg
    │       │       │   │       ├───venus-mars.svg
    │       │       │   │       ├───vest.svg
    │       │       │   │       ├───vest-patches.svg
    │       │       │   │       ├───vial.svg
    │       │       │   │       ├───vials.svg
    │       │       │   │       ├───video.svg
    │       │       │   │       ├───video-slash.svg
    │       │       │   │       ├───vihara.svg
    │       │       │   │       ├───virus.svg
    │       │       │   │       ├───virus-slash.svg
    │       │       │   │       ├───viruses.svg
    │       │       │   │       ├───voicemail.svg
    │       │       │   │       ├───volleyball-ball.svg
    │       │       │   │       ├───volume-down.svg
    │       │       │   │       ├───volume-mute.svg
    │       │       │   │       ├───volume-off.svg
    │       │       │   │       ├───volume-up.svg
    │       │       │   │       ├───vote-yea.svg
    │       │       │   │       ├───vr-cardboard.svg
    │       │       │   │       ├───walking.svg
    │       │       │   │       ├───wallet.svg
    │       │       │   │       ├───warehouse.svg
    │       │       │   │       ├───water.svg
    │       │       │   │       ├───wave-square.svg
    │       │       │   │       ├───weight.svg
    │       │       │   │       ├───weight-hanging.svg
    │       │       │   │       ├───wheelchair.svg
    │       │       │   │       ├───wifi.svg
    │       │       │   │       ├───wind.svg
    │       │       │   │       ├───window-close.svg
    │       │       │   │       ├───window-maximize.svg
    │       │       │   │       ├───window-minimize.svg
    │       │       │   │       ├───window-restore.svg
    │       │       │   │       ├───wine-bottle.svg
    │       │       │   │       ├───wine-glass.svg
    │       │       │   │       ├───wine-glass-alt.svg
    │       │       │   │       ├───won-sign.svg
    │       │       │   │       ├───wrench.svg
    │       │       │   │       ├───x-ray.svg
    │       │       │   │       ├───yen-sign.svg
    │       │       │   │       └───yin-yang.svg
    │       │       │   └───webfonts/
    │       │       │       ├───fa-brands-400.eot
    │       │       │       ├───fa-brands-400.svg
    │       │       │       ├───fa-brands-400.ttf
    │       │       │       ├───fa-brands-400.woff
    │       │       │       ├───fa-brands-400.woff2
    │       │       │       ├───fa-regular-400.eot
    │       │       │       ├───fa-regular-400.svg
    │       │       │       ├───fa-regular-400.ttf
    │       │       │       ├───fa-regular-400.woff
    │       │       │       ├───fa-regular-400.woff2
    │       │       │       ├───fa-solid-900.eot
    │       │       │       ├───fa-solid-900.svg
    │       │       │       ├───fa-solid-900.ttf
    │       │       │       ├───fa-solid-900.woff
    │       │       │       └───fa-solid-900.woff2
    │       │       ├───jquery/
    │       │       │   ├───jquery.js
    │       │       │   ├───jquery.min.js
    │       │       │   ├───jquery.min.map
    │       │       │   ├───jquery.slim.js
    │       │       │   ├───jquery.slim.min.js
    │       │       │   └───jquery.slim.min.map
    │       │       └───jquery-easing/
    │       │           ├───jquery.easing.compatibility.js
    │       │           ├───jquery.easing.js
    │       │           └───jquery.easing.min.js
    │       └───templates/
    │           ├───index.ftl
    │           ├───login.ftl
    │           ├───sample.ftl
    │           ├───admin/
    │           │   ├───adminHome.ftl
    │           │   ├───applyList.ftl
    │           │   └───tenantsList.ftl
    │           ├───common/
    │           │   ├───layout-sample.ftl
    │           │   ├───layout.ftl
    │           │   ├───sidebar.ftl
    │           │   └───topbar.ftl
    │           ├───error/
    │           │   └───error.ftl
    │           ├───member/
    │           │   ├───join.ftl
    │           │   └───memberList.ftl
    │           └───property/
    │               ├───propertyList.ftl
    │               ├───register.ftl
    │               └───unit/
    │                   ├───addRoom.ftl
    │                   └───register.ftl
    └───test/
        ├───java/
        │   └───com/
        │       └───jjst/
        │           └───rentManagement/
        │               └───renthouse/
        │                   ├───RentHouseApplicationTests.java
        │                   └───util/
        │                       └───UtilityTest.java
        └───resources/
            └───application-local.properties
```

## 할 일 목록

프로젝트 구조를 기반으로 한 일반적인 할 일 목록입니다. 구체적인 작업이 발생하면 세분화할 수 있습니다.

### 높은 우선순위
- **OAuth2 클라이언트 구성:** `application-dev.properties` (및 기타 환경별 속성 파일)에 Naver 및 Kakao에 대한 올바른 클라이언트 ID 및 시크릿이 구성되어 있는지 확인하십시오.
- **오류 처리:** `GlobalExceptionHandler.java`를 검토하여 모든 잠재적인 예외가 정상적으로 처리되고 사용자 친화적인 오류 메시지가 제공되는지 확인하십시오.

### 출시 전 우선순위
- **보안 구성 검토:** `WebSecurityConfig.java`에는 `csrf().disable()` 및 `requestMatchers("/admin/**").permitAll()`이 있습니다. 프로덕션 환경에서는 이러한 부분을 적절하게 검토하고 보안을 강화해야 합니다.
    - CSRF 보호 활성화.
    - `/admin/**` 엔드포인트에 대한 적절한 역할 기반 접근 제어 구현.

### 중간 우선순위
- **데이터베이스 관리:**
    - 스키마 진화를 위한 데이터베이스 마이그레이션 (예: Flyway 또는 Liquibase) 구현.
    - H2가 개발용으로만 사용되는 경우 프로덕션용으로 더 강력한 데이터베이스 (예: PostgreSQL, MySQL) 고려.
- **테스트:**
    - 모든 서비스, 컨트롤러 및 유틸리티 클래스에 대한 단위 및 통합 테스트 확장.
    - 중요한 사용자 흐름에 대한 엔드투엔드 테스트 구현.
- **코드 품질:**
    - 코드 품질 및 일관성을 유지하기 위해 정적 코드 분석 도구 (예: SonarQube, Checkstyle, PMD) 구현.
    - 애플리케이션 전체에 걸쳐 일관된 로깅 관행을 보장.
- **프론트엔드 개선:**
    - 모든 FreeMarker 템플릿에 대한 사용자 경험 (UX) 및 사용자 인터페이스 (UI) 개선.
    - 사용자에게 즉각적인 피드백을 제공하기 위해 클라이언트 측 폼 유효성 검사 구현.

### 낮은 우선순위
- **문서화:**
    - 모든 Java 클래스 및 메서드에 Javadoc/코드 주석 추가.
    - 포괄적인 API 문서 (예: OpenAPI/Swagger) 생성.
- **성능 최적화:**
    - 애플리케이션 프로파일링을 통해 성능 병목 현상 식별 및 해결.
    - 데이터베이스 쿼리 및 ORM 사용 최적화.
- **배포 자동화:**
    - 지속적인 통합 및 배포를 위해 Jenkins 파이프라인 (`jenkins/build-pipeline.txt`) 개선.
    - Docker를 사용하여 애플리케이션 컨테이너화.