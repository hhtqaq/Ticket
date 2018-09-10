function transformDate(obj){
				var t=	$(obj).html();
				if(t.indexOf("-")!=-1){
					return;
				}
				var Odate=new Date((Number)(t));
				var realDate=Odate.getFullYear()+"-"+(Odate.getMonth()+1)+"-"+Odate.getDate()+" "+Odate.getHours()+":"+Odate.getMinutes();
				$(obj).html(realDate);
			}