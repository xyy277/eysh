const gulp = require('gulp');
const minifycss = require('gulp-minify-css');
const fileinclude = require('gulp-file-include');
const sass = require('gulp-sass');
const spritesmith = require('gulp.spritesmith');
const uglify = require('gulp-uglify');
const autoprefixer = require('gulp-autoprefixer');
const rename = require('gulp-rename');
const imagemin = require('gulp-imagemin');
const chalk = require('chalk');
const clean  = require('gulp-clean');
const browserSync = require('browser-sync').create();

const distPath = '.';

gulp.task('browser-sync', () => {
    const files = [
        'qwui/html/**/*.html',
        'qwui/js/**/*.js'
    ];

    browserSync.init(files, {
        server: {
            baseDir: distPath,
            index: '/html/'
        },
        port: 8080,
        notify: false, //刷新是否提示
        open: true //是否自动打开页面
    });
});


gulp.task('style', () => {
    return gulp.src('./sass/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('./styles/'))
        .pipe(browserSync.stream()); //browserSync:只监听sass编译之后的css
});

gulp.task('buildBootstrap', () => {
    return gulp.src('./qwui/sass/bootstrap/bootstrap.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(autoprefixer({
            browsers: ['last 2 versions', 'last 2 Explorer versions', '> 5%']
        }))
        .pipe(minifycss())
        .pipe(rename('js/vendor/bootstrap/css/bootstrap.min.css'))
        .pipe(gulp.dest(distPath))
        .pipe(browserSync.stream()); //browserSync:只监听sass编译之后的css
});

gulp.task('prefixer', () => {
    return gulp.src('dist/css/*.css')
        .pipe(autoprefixer({
            browsers: ['last 2 versions', 'last 2 Explorer versions', '> 5%']
        }))
        .pipe(gulp.dest(`${distPath}/css/`))
})

gulp.task('html', () => {
    return gulp.src(['qwui/html/**/*.html', '!qwui/html/include/*.html'])
        .pipe(fileinclude({
            prefix: '@@',
            basepath: '@file'
        }))
        .pipe(gulp.dest(`${distPath}/html`))
});

gulp.task('js', () => {
    return gulp.src(['qwui/js/pages/**/*.js'])
        .on('error', error => {
            console.log(chalk.red(error));
        })
        //.pipe(uglify())
        .pipe(gulp.dest(`${distPath}/js/pages/`))
});

const argv = require('minimist')(process.argv.slice(2));

const spritesMithConfig = {
    imgName: argv.name + '.png',
    cssName: '_' + argv.name + '.scss',
    cssFormat: 'scss',
    algorithm: 'binary-tree',
    imgPath: '../images/sprite/' + argv.name + '.png',
    padding: 8
}

gulp.task('sprite', () => {
    const spriteData =
        gulp.src('./qwui/images/sprites/' + argv.name + '/*')
            .pipe(spritesmith(spritesMithConfig));

    spriteData.img.pipe(gulp.dest("./qwui/images/sprite"));
    spriteData.img.pipe(gulp.dest(`${distPath}/images/sprite`));
    spriteData.css.pipe(gulp.dest("./qwui/sass/module/icon"));
})

gulp.task('packVendorJs', () => {
    return gulp.src(['qwui/js/vendor/**/*'])
        .on('error', error => {
            console.log(chalk.red(error));
        })
        .pipe(gulp.dest(`${distPath}/js/vendor/`))
});

gulp.task('packImages', () => {
    return gulp.src(['qwui/images/**/*', '!qwui/images/sprites/**/*'])
        .pipe(gulp.dest(`${distPath}/images/`))
});

gulp.task('packMinImages', () => {
    return gulp.src(['qwui/images/**/*', '!qwui/images/sprites/**/*'])
        .pipe(imagemin({
            optimizationLevel: 3,
            progressive: true,
            interlaced: true
        }))
        .pipe(gulp.dest(`${distPath}/images/`))
});

gulp.task('packClean', () => {
    return gulp.src(distPath)
        .on('error', error => {
            console.log(chalk.red(error));
        })
        .pipe(clean({ force: true }))
});

gulp.task('packAssets' ,() => {
    // gulp.run('html');
    gulp.run('style');
    // gulp.run('js');
    // gulp.run('packVendorJs');
    // gulp.run('packImages');
})

// 执行任务
gulp.task('qwui', () => {
    //gulp.run('browser-sync');
    gulp.run('packAssets');
    gulp.watch(['sass/**/*.scss'], ['style']);
    //gulp.watch(['qwui/html/**/*.html'], ['html']);
    //gulp.watch(['qwui/js/pages/*.js'], ['js']);
    //gulp.watch(['qwui/js/vendor/**/*'], ['packVendorJs']);
    //gulp.watch(['qwui/images/**/*'], ['packImages']);
});
