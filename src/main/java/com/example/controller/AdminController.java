package com.example.controller;

import com.example.aoplog.Log;
import com.example.entity.*;
import com.example.service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

/**
 * @author JK
 */
@Controller
@RequestMapping("/servlet")
public class AdminController
{
	@Resource
	UserService userService;
//	@Resource
//	AdminService adminService;
	@Resource
	MenuService menuService;
	@Resource
	RoleMenuService roleMenuService;
	@Resource
	DocumentService documentService;
	@Resource
	Userinfo user;
	@Resource
	Datatable datatable;
//	@Resource
//	Admin admin;

	//	管理路径跳转的问题--前台的界面(路径/front/*)
	@RequestMapping("/pfront/{id}")
	public String matchUrl(@PathVariable("id") String id)
	{
		System.out.println("id=" + id);
		return "/front/" + id;
	}

	@RequestMapping("/p/{id}")
	public String matchUrl2(@PathVariable("id") String id)
	{
		System.out.println("id=" + id);
		return "/front/" + id;
	}

	//管理路径跳转的问题--后台的界面(路径/back/*)
	@RequestMapping("/pback/{id}")
	public String match2Url(@PathVariable("id") String id)
	{
		System.out.println("id=" + id);
		return "/back/" + id;
	}
	//	@RequestMapping("/test")
	//	public ModelAndView test()
	//	{
	//		ModelAndView modelAndView = new ModelAndView();
	//		modelAndView.setViewName("index");
	//		System.out.println(modelAndView);
	//		return modelAndView;
	//	}
	//
	//	@RequestMapping("/testStr")
	//	public String testStr()
	//	{
	//		return "index";
	//	}

	//	验证码的判断使用
	@RequestMapping("/vacode")
//	@Log(operationType = "", operationName = "用户登录")
	public void testCode(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("进来这个方法里面=========================");
		//BufferedImage将图片存入缓存中，有三个构造方法，此处的三个参数为图片的宽，高，以及创建的图像类型。
		BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		//为bi创建图形上下文
		Graphics g = bi.getGraphics();
		//设置颜色，此处调用的构造方法是基于RGB数值作为参数的
		Color c = new Color(200, 150, 255);
		//设置颜色
		g.setColor(c);
		//该方法用于填充指定的矩形，参数是坐标和宽高
		g.fillRect(0, 0, 68, 22);

		//编写随机获取验证码的部分

		//将字符串转换为字符数组
		char[] ch = "abcdefghjklmnopqrstuvwxyz023456789".toCharArray();
		//随机类，在本程序中只使用了 int nextInt(int n) 方法，作用是生成一个0-n的伪随机int值
		Random r = new Random();

		int len = ch.length, index;

		//用于存储随机生成的四位验证码
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 4; i++)
		{
			//从0-len随机获取一个作为下标
			index = r.nextInt(len);
			//随机获取颜色
			g.setColor(new Color(r.nextInt(200), r.nextInt(150), r.nextInt(255)));

			//在图形中绘制指定的String，参数对应要绘制的String以及坐标
			g.drawString(ch[index] + " ", (i * 15) + 3, 18);

			//将内容添加到StringBuffer
			sb.append(ch[index]);
		}

		//将验证码信息放入session中用于验证
		request.getSession().setAttribute("PicCode", sb.toString());
		//将文件流输出，参数要写入的RenderedImage，输出的文件格式，输出到的ImageOutputStream
		ImageIO.write(bi, "JPG", response.getOutputStream());
	}

	//前台登录
	@RequestMapping("/front/login")
	@Log(operationType = "登录", operationName = "用户登录")
	public void testLogin(String username, String password, String checkCode, HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().setAttribute("username", username);
		user.setUsername(username);
		String md5pwd = MD5Utils.md5(password);
		System.out.println("密码是：" + md5pwd);
		user.setPassword(md5pwd);

		Userinfo users = userService.findUser(user);
		System.out.println(users);
		if (null != users)
		{
			request.getSession().setAttribute("loginUser", users);
			String PicCode = (String) request.getSession().getAttribute("PicCode");
			checkCode = checkCode.toLowerCase();
			if (checkCode.equals(PicCode))
			{
				System.out.println("前台验证码成功！");
				List<MenusInfo> menuList1 = menuService.findMenuID(users.getRoleid());
				//对应的hashMap是线性结构，是无序的，但是LinkedHashMap是有序的内容
				Map<String, List<MenusInfo>> menumap = new LinkedHashMap<>();
				//子菜单
				for (int i = 0; i < menuList1.size(); i++)
				{
					MenusInfo menusInfo = menuList1.get(i);
					Map<String, Object> getroleMap = new HashMap<>();
					getroleMap.put("roleid", users.getRoleid());
					getroleMap.put("menuparentid", menusInfo.getMenuid());
					List<MenusInfo> menuList2 = menuService.findMenusID(getroleMap);
					menumap.put(menusInfo.getMenuname(), menuList2);
				}
				request.getSession().setAttribute("menuMap", menumap);
				ResponseUtils.outJson(response, users);
			} else
			{
				System.out.println("前台验证码失败！");
				ResponseUtils.outHtml(response, "验证码错误");
			}
		} else
		{
			ResponseUtils.outHtml(response, "登录失败");
		}
	}

	//查找该用户是否已经注册过
	@RequestMapping("/selectName")
	@Log(operationType = "注册", operationName = "注册登录")
	public void selectName(String username, HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("对应的用户名："+username);
		try
		{
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset='UTF-8'");

//		调用Dao文件中的方法
		Userinfo userinfo = userService.selectFrontName(username);

//		判断后台填写的值是否与数据库中的值一致
		if (null!=userinfo)
		{
			System.out.println("该姓名已经存在");
			ResponseUtils.outHtml(response, "success");

		} else
		{
			System.out.println("姓名可以注册");
			ResponseUtils.outHtml(response, "error");
		}
	}

	//注册登录
	@RequestMapping("/reginster")
	@Log(operationType = "注册", operationName = "注册登录")
	public void testReginster(Userinfo userinfo, HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, Object> map = new HashMap();
		map.put("username", userinfo.getUsername());
		String md5pwd = MD5Utils.md5(userinfo.getPassword());
		map.put("password", md5pwd);
		map.put("usersex", userinfo.getUsersex());
		map.put("schoolmate", userinfo.getSchoolmate());
		map.put("position", userinfo.getPosition());
		map.put("cellphone", userinfo.getCellphone());
		map.put("usermail", userinfo.getUsermail());
		map.put("userstate","审核通过");
		map.put("roleid",2);

		int result = userService.addUser(map);
		System.out.println(map);
		if (result > 0)
		{
			request.getSession().setAttribute("username", userinfo.getUsername());
			System.out.println("注册成功");
			ResponseUtils.outHtml(response, "注册成功");
		} else
		{
			System.out.println("注册失败");
			ResponseUtils.outHtml(response, "注册成功");
		}
	}

	//	后台登录
	@RequestMapping("/back/admin_login")
	@Log(operationType = "后台登录", operationName = "管理员登录")
	public void testAdminLogin(@RequestParam(value = "account", required = true) String adaccount, String adpassword, HttpServletRequest request, HttpServletResponse response)
	{
		request.getSession().setAttribute("username", adaccount);
		user.setUsername(adaccount);
		String md5pwd = MD5Utils.md5(adpassword);
		user.setPassword(md5pwd);
		System.out.println("后台传的数据"+user);
		Userinfo admins = userService.findLogin(user);
		System.out.println("后台得到="+admins);
		if (null != admins)
		{
			request.getSession().setAttribute("loginAdmin", admins);
			request.getSession().setAttribute("roleid", admins.getRoleid());
			System.out.println("后台登录成功");
			List<MenusInfo> menuList1 = menuService.findMenuID(admins.getRoleid());
			//对应的hashMap是线性结构，是无序的，但是LinkedHashMap是有序的内容
			Map<String, List<MenusInfo>> menumap = new LinkedHashMap<>();
			//子菜单
			for (int i = 0; i < menuList1.size(); i++)
			{
				MenusInfo menusInfo = menuList1.get(i);
				Map<String, Object> getroleMap = new HashMap<>();
				getroleMap.put("roleid", admins.getRoleid());
				getroleMap.put("menuparentid", menusInfo.getMenuid());
				List<MenusInfo> menuList2 = menuService.findMenusID(getroleMap);
				menumap.put(menusInfo.getMenuname(), menuList2);
			}
			request.getSession().setAttribute("menuMap", menumap);
			ResponseUtils.outJson(response, admins);
		} else
		{
			System.out.println("后台登录失败");
			ResponseUtils.outHtml(response, "登录失败");
		}
	}
	//用户退出:
	@RequestMapping("/front/exit")
	public void userexit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset='UTF-8'");

		//获取session对象
		HttpSession hs = request.getSession();
		//强制销毁session
		hs.invalidate();
		//重定向到登录页面
		response.sendRedirect(request.getContextPath() + "/servlet/pfront/login");
	}

	//管理员退出:
	@RequestMapping("/back/exit")
	public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
		{
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("charset='UTF-8'");

		//获取session对象
		HttpSession hs = request.getSession();
		//强制销毁session
		hs.invalidate();
		//重定向到登录页面
		response.sendRedirect(request.getContextPath() + "/servlet/pback/admin_login");
	}

	@ResponseBody
	// @ResponseBody是作用在方法上的，@ResponseBody 表示该方法的返回结果直接写入 HTTP response body 中，
	// 一般在异步获取数据时使用【也就是AJAX】。
	// 注意：在使用 @RequestMapping后，返回值通常解析为跳转路径，但是加上 @ResponseBody
	// 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中。
	// 比如异步获取 json 数据，加上 @ResponseBody 后，会直接返回 json 数据。
	// @RequestBody 将 HTTP 请求正文插入方法中，使用适合的 HttpMessageConverter 将请求体写入某个对象。
	@RequestMapping("/front/fileact")
	public void uploadFile(@RequestParam("file") MultipartFile fileact, String test,Document document, HttpServletRequest request, HttpServletResponse response)
	{
		//		MultipartResolver的作用：
		//		（1）把客户端上传的文件流转换成MultipartFile 封装类
		//		（2）通过MultipartFile封装类获取到文件流
		Userinfo user = (Userinfo) request.getSession().getAttribute("loginUser");
		System.out.println("获取的文件信息：" + user);
		//文件名的内容显示：（文件名包含格式）
		String originalFileName = fileact.getOriginalFilename();
		System.out.println(fileact.getOriginalFilename());
		System.out.println(test);
		Map<String, Object> map = new HashMap<>();
		try
		{
			//改成新的文件名字
			String newname = document.getDocumentname();
			String content = document.getDocumentcontent();
			int downloadscore = document.getDownloadscore();

			System.out.println(newname+content+downloadscore);

			//获取文件类型，以最后一个`.`为标识
			String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			System.out.println("文件类型：" + type);
			//获取文件名称（不包含格式）
//			String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

			//获取当前的时间
			//使用Date创建日期对象
			Date date = new Date();
			System.out.println("当前的日期是------>" + date);

			/**
			 * 创建格式化时间日期类
			 *构造入参String类型就是我们想要转换成的时间形式
			 */
			SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			System.out.println("格式化后的时间------->" + formattime.format(date));
			String datetime = formattime.format(date);
			System.out.println(datetime);

			Map<String, Object> mapAdd = new HashMap<>();
			if (null != newname && "" != newname)
			{
				mapAdd.put("documentname", newname);
			}
			if (null != user.getUsername() && "" != user.getUsername())
			{
				mapAdd.put("uploadpeople", user.getUsername());
			}
			if (null != datetime && "" != datetime)
			{
				mapAdd.put("uploadtime", datetime);
			}
			mapAdd.put("downloadscore", downloadscore);
			mapAdd.put("documentcontent",content);
			if (null != type && "" != type)
			{
				mapAdd.put("documenttype", type);
			}
			System.out.println("对应的map="+mapAdd);
			int result = documentService.addDocument(mapAdd);

			fileact.transferTo(new File("E:\\IdeaProjects\\file\\src\\main\\webapp\\WEB-INF\\upload\\" + newname + "." + type));
			map.put("code", 0);
			map.put("msg", "");
			map.put("data", "{}");
			ResponseUtils.outJson(response, map);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//	下载文件信息
	@RequestMapping("/back/download")
	public ResponseEntity<byte[]> download(String documentname, String documenttype, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//设置下载的文件名
		String filename = documentname + "." + documenttype;
		//路径
		String path = "E:\\IdeaProjects\\file\\src\\main\\webapp\\WEB-INF\\upload\\" + filename;
		//得到要下载的文件
		File file=new File(path);
		HttpHeaders headers = new HttpHeaders();
		//设置文件下载头
		String fileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
		headers.setContentDispositionFormData("attachment", fileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}

	//权限的菜单的显示
	@RequestMapping("/back/selectMenu")
	@Log(operationType = "菜单显示", operationName = "管理员菜单")
	public void selectTreeMenu(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int roleid = (int) request.getSession().getAttribute("roleid");
		//总的菜单的内容
		List<TreeData> treeDataAll = roleMenuService.findAllID();
		//已选的----外面传的参数是一个集合，里面的这个也是一个集合才行
		List<TreeData> treeData1 = roleMenuService.findRMID(roleid);
		Map<String, Object> map = new HashMap<>();
		map.put("menuAll", treeDataAll);
		map.put("menuroleId", treeData1);
		ResponseUtils.outJson(response, map);
	}

	//	更新菜单的内容信息
	@RequestMapping("/back/updateTreeMenu")
	@Log(operationType = "菜单更新", operationName = "管理员菜单")
	public void updateTreeMenu(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//		角色
		String roleid = request.getParameter("roleid");
		System.out.println("内容什么" + roleid);
		//		菜单的ID值
		String[] menuid = request.getParameterValues("menuid[]");

		//		删除所有对应角色的菜单内容
		roleMenuService.delMenu(Integer.parseInt(roleid));
		//		把查找到的对应的所有的勾选的菜单全部加进来--数组的遍历显示进行对应的添加
		for (String s : menuid)
		{
			Map<String, Object> map = new HashMap<>();
			map.put("roleid", roleid);
			map.put("menuid", Integer.parseInt(s));
			roleMenuService.insertMenu(map);
		}
		ResponseUtils.outHtml(response, "success");
	}

	//用户信息的显示
	@RequestMapping("/back/layuiTable")
	@Log(operationType = "用户信息显示", operationName = "管理用户信息")
	public void layuiTable(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String username = request.getParameter("username");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");
		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		System.out.println("用户名是="+username);
		//		获取对应的id值
		Map<String, Object> map = new HashMap<>();
		if (null != username && "" != username)
		{
			map.put("username", username);
		}
		if (null != beginTime && "" != beginTime)
		{
			map.put("beginTime", beginTime);
		}
		if (null != overTime && "" != overTime)
		{
			map.put("overTime", overTime);
		}
		int pages = (pageInt - 1) * limitInt;
		int limits = limitInt;
		map.put("pageInt", pages);
		map.put("limitInt", limits);

		System.out.println("用户信息="+map);
		List<Userinfo> users = userService.findAll(map);
		System.out.println("输出成功" + users.toString());
		datatable.setCode(0);
		datatable.setMsg("");
		datatable.setCount(100);
		datatable.setData(users);
		ResponseUtils.outJson(response, datatable);
	}

	//用户状态的修改
	@RequestMapping("/back/updateState")
	@Log(operationType = "用户状态修改", operationName = "管理用户状态")
	public void updateState(String userid,String userstate,HttpServletRequest request, HttpServletResponse response)
	{
		String upuserstate = null;
		if(userstate.equals("审核通过")){
			upuserstate="禁用";
		}else{
			upuserstate="审核通过";
		}
		System.out.println(userid+"="+upuserstate);
		Map<String,Object> map = new HashMap<>();
		map.put("userid",Integer.parseInt(userid));
		map.put("userstate",upuserstate);
		int result = userService.updateState(map);
		if (result > 0)
		{
			ResponseUtils.outHtml(response, "success");
		} else
		{
			ResponseUtils.outHtml(response, "error");
		}
	}

	//删除对应表格的内容的值
	@Log(operationType = "用户信息删除", operationName = "管理用户信息")
	@RequestMapping("/back/delTable")
	public void delTable(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String id = request.getParameter("userid");
		System.out.println("删除servlet" + id);
		int userid = Integer.valueOf(id);

		int result = userService.deleteById(userid);
		if (result > 0)
		{
			ResponseUtils.outHtml(response, "删除成功");
		} else
		{
			ResponseUtils.outHtml(response, "删除失败");
		}
	}

	//更新对应表格的内容的值
	@RequestMapping("/back/updateTable")
	@Log(operationType = "用户信息更新", operationName = "管理用户信息")
	public void updateTable(String userid, HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//		String id = request.getParameter("userid");
		System.out.println("servlet" + userid);
		int userids = Integer.valueOf(userid);
		String username = request.getParameter("username");
		String addscore = request.getParameter("addscore");
		String userstate = request.getParameter("userstate");

		user.setUserid(userids);
		user.setUsername(username);
		user.setAddscore(Integer.parseInt(addscore));
		user.setUserstate(userstate);

		System.out.println(user + "内容是");
		int result = userService.updateById(user);
		if (result > 0)
		{
			ResponseUtils.outHtml(response, "更新成功");
		} else
		{
			ResponseUtils.outHtml(response, "更新失败");
		}
	}

//	//添加对应的表格的信息
//	@RequestMapping("/back/addForm")
//	@Log(operationType = "用户信息添加", operationName = "管理用户信息")
//	protected void form(Userinfo userinfo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//	{
//		Map<String, String> map = new HashMap();
//		map.put("username", userinfo.getUsername());
//		String md5pwd = MD5Utils.md5(userinfo.getPassword());
//		map.put("password", md5pwd);
//		map.put("usersex", userinfo.getUsersex());
////		map.put("realname", userinfo.getRealname());
//
//		int result = userService.addUser(map);
//		if (result > 0)
//		{
//			ResponseUtils.outHtml(response, "注册成功");
//		} else
//		{
//			ResponseUtils.outHtml(response, "注册失败");
//		}
//	}

	//文档信息的显示
	@RequestMapping("/back/documentTable")
	@Log(operationType = "文档信息显示", operationName = "管理文档信息")
	public void documentTable(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		String documentname = request.getParameter("documentname");
		String uploadpeople = request.getParameter("uploadpeople");
		String beginTime = request.getParameter("beginTime");
		String overTime = request.getParameter("overTime");
		String documenttype = request.getParameter("documenttype");
		int pageInt = Integer.valueOf(page);
		int limitInt = Integer.valueOf(limit);

		//获取对应的id值
		Map<String, Object> map = new HashMap<>();
		if (null != documentname && "" != documentname)
		{
			map.put("documentname", documentname);
		}
		if (null != uploadpeople && "" != uploadpeople)
		{
			map.put("uploadpeople", uploadpeople);
		}
		if (null != beginTime && "" != beginTime)
		{
			map.put("beginTime", beginTime);
		}
		if (null != overTime && "" != overTime)
		{
			map.put("overTime", overTime);
		}
		if (null != documenttype && "" != documenttype && !documenttype.equals("暂无"))
		{
			System.out.println(documenttype);
			map.put("documenttype", documenttype);
		}

		int pages = (pageInt - 1) * limitInt;
		int limits = limitInt;
		map.put("pageInt", pages);
		map.put("limitInt", limits);

		List<Userinfo> users = documentService.findDocument(map);
		System.out.println("输出成功" + users.toString());
		datatable.setCode(0);
		datatable.setMsg("");
		datatable.setCount(100);
		datatable.setData(users);
		ResponseUtils.outJson(response, datatable);
	}

	//文档的审核条件--通过的修改
	@RequestMapping("/back/approved")
	@Log(operationType = "文档信息审核", operationName = "管理文档信息")
	public void documentapproved(String uploadtime,HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int result = documentService.updateapproved(uploadtime);
		if (result > 0)
		{
			ResponseUtils.outHtml(response, "审核通过成功");
		} else
		{
			ResponseUtils.outHtml(response, "审核通过失败");
		}
	}
	//文档的审核条件--不通过的修改
	@RequestMapping("/back/noapproved")
	@Log(operationType = "文档信息不审核", operationName = "管理文档信息")
	public void documentnoapproved(String uploadtime,HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int result = documentService.updatenoapproved(uploadtime);
		if (result > 0)
		{
			ResponseUtils.outHtml(response, "审核不通过成功");
		} else
		{
			ResponseUtils.outHtml(response, "审核不通过失败");
		}
	}
}
