package com.example.myBlog.cache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.myBlog.dto.TagCacheDTO;

public class TagCache {

	public static List<TagCacheDTO> getTag() {
		List<TagCacheDTO> list = new ArrayList<>();

		String[] name = { "开发语言", "平台框架", "服务器", "数据库和缓存", "开发工具", "系统设备", "其它" };

		String[][] value = {
				{ "javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang",
						"objective-c", "typescript", "shell", "c#", "swift", "sass", "bash", "ruby", "less", "asp.net",
						"lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl" },
				{ "aravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa",
						"struts" },
				{ "linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存", "tomcat", "负载均衡", "unix", "hadoop",
						"windows-server" },
				{ "mysql", "redis", "mongodb", "sql", "oracle", "nosql", "memcached", "sqlserver", "postgresql",
						"sqlite" },
				{ "git", "github", "visual-studio-code", "vim", "sublime-text", "xcode", "intellij-idea", "eclipse",
						"maven", "ide", "svn", "visual-studio", "atom", "emacs", "textmate", "hg" },
				{ "android", "ios", "chrome", "windows", "iphone", "firefox", "internet-explorer", "safari", "ipad",
						"opera", "apple-watch" },
				{ "" } };

		for (int i = 0; i < name.length; i++) {
			TagCacheDTO tagCacheDTO = new TagCacheDTO();
			tagCacheDTO.setTagCacheName(name[i]);
			tagCacheDTO.setTagCacheValue(Arrays.asList(value[i]));
			list.add(tagCacheDTO);
		}
		return list;

	}

	public static String filterInvalid(String tags) {
		String[] split = tags.split("，");
		List<TagCacheDTO> tagDTO = getTag();
		List<String> tagList = tagDTO.stream().flatMap(tag -> tag.getTagCacheValue().stream())
				.collect(Collectors.toList());
		String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining("，"));

		return invalid;
	}
}
