package entity;

public class ChangeShifts {
	


		private String rid;
		private String cid; 
		private String dplace;
		private String aplace;
		public String getDplace() {
			return dplace;
		}
		public void setDplace(String dplace) {
			this.dplace = dplace;
		}
		public String getAplace() {
			return aplace;
		}
		@Override
		public String toString() {
			return "ChangeShifts [rid=" + rid + ", cid=" + cid + ", dplace=" + dplace + ", aplace=" + aplace
					+ ", dtime=" + dtime + ", atime=" + atime + "]";
		}
		public void setAplace(String aplace) {
			this.aplace = aplace;
		}
		private String dtime;
		private String atime;
		public String getRid() {
			return rid;
		}
		public void setRid(String rid) {
			this.rid = rid;
		}
		public String getCid() {
			return cid;
		}
		public void setCid(String cid) {
			this.cid = cid;
		}
		public String getDtime() {
			return dtime;
		}
		public void setDtime(String dtime) {
			this.dtime = dtime;
		}
		public String getAtime() {
			return atime;
		}
		public void setAtime(String atime) {
			this.atime = atime;
		}
		
		

	}



