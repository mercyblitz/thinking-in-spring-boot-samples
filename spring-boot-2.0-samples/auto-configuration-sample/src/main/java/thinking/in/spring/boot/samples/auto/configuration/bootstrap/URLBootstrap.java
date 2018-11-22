/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package thinking.in.spring.boot.samples.auto.configuration.bootstrap;

import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL 资源引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class URLBootstrap {

    public static void main(String[] args) throws Exception {
        // 构建 URL 对象
        URL url = new URL("https://github.com/mercyblitz");
        // 获取 URLConnection 对象
        URLConnection urlConnection = url.openConnection();
        try (InputStream inputStream = urlConnection.getInputStream()) { // 自动关闭 InputStream
            // 复制 资源流 到 标准输出流
            StreamUtils.copy(inputStream, System.out);
        }
    }
}
