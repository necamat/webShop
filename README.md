# WebShop


![Capture](https://user-images.githubusercontent.com/79508257/128847434-b297f196-30b8-4dd9-864e-c9a9a816c7aa.JPG)


<p>The application is conceived as a Web Shop, in which customers are known. They can only be added by the administrator. So the whole application is a closed type, a kind of main warehouse and customers who are their well-known distributors (stores). Home I About us are pages that are advertising and available to all visitors. For all other pages it is necessary to log in, authorizations and access are in accordance with the roles that users receive from the administrator. The application was created for general use (not specialized activities), so for the purpose of more picturesque use, I use images, logo, name of my own company.</p>
  <p><strong>Spring MVC 4.1.6.</strong> was used for application development, <strong>Spring Security 4.0.1.</strong> for protection and authentication, and <strong>Hibernate 4.3.0.</strong> for integration with <strong>MySql</strong> database. <strong>CRUD</strong> operations are performed within transactions. Password storage is done with the help of <strong>Bcrypt</strong>, and user memory functionality with the help of a custom implementation of <strong>PersistentTokenRepository</strong> with <strong>Hibernate, HibernateTokenRepositoryImpl</strong>. The <strong>SequenceStyleGenerator</strong> is used to generate unique primary keys. For display we use <strong>Java Server Pages, Bootstrap 4.6., Jquery-3.5.1., JavaScrip, HTML, CSS </strong>.</p>
  <p>For a detailed explanation see documentation.dox file </p>
