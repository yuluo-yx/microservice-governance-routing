<template >
    <div class="footer">
        <p class="copyright">CopyRight@Arthas by yuluo</p>
    </div>
</template>
  
<style lang="scss">
.content {
    color: rgb(61, 61, 61);
    display: flex;
    justify-content: space-evenly;

    ul {
        float: left;
        text-align: center;

        h5 {
            color: rgb(61, 61, 61);
            font-weight: 400;
            font-size: 22px;
        }

        li {
            list-style: none;
            float: left;

            .el-link {
                font-size: 18px;
            }
        }
    }
}

.content .licontent:nth-child(2) {
    h5 {
        padding-left: 50px;
    }

    li {
        // margin-left: -1vw;
        font-size: 18px;
        text-align: center;
        padding-left: 50px;
    }
}

.content .licontent:nth-child(3) {
    li {
        // margin-left: 0.3vw;
        font-size: 18px;
        text-align: center;
    }
}

.content .licontent:nth-child(4) {
    li {
        // margin-left: 0.3vw;
        font-size: 18px;
        text-align: center;
    }
}

.licontent {
    width: 14vw;
}

.footer {
    padding-top: 30px;
    height: 28vh;
    background: linear-gradient(rgb(62, 255, 225), rgb(19, 255, 247))
}

.copyright {
    text-align: center;
    width: 100%;
    padding-top: 20px;
    padding-bottom: 5vh;
    font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    font-size: 20px
}
</style>