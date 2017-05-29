#include <jpeglib.h>

// Modified from https://github.com/LionNatsu/greenError
class JpegCompress
{
public:
	JOCTET* buffer;
	jpeg_compress_struct* cinfo;
	
private:
	static void init_buffer(jpeg_compress_struct* cinfo)
	{
		
	};
	
	static boolean empty_buffer(jpeg_compress_struct* cinfo)
	{
		return TRUE;
	};
	
	static void term_buffer(jpeg_compress_struct* cinfo)
	{
		
	};
	
public:
	JpegCompress(int width,int height)
	{
		this->cinfo=new jpeg_compress_struct;
		this->buffer=new JOCTET[width*height*3];
		
		jpeg_error_mgr* jerr=new jpeg_error_mgr;
		cinfo->err=jpeg_std_error(jerr);
		
		jpeg_create_compress(cinfo);
		
		jpeg_destination_mgr* jdest=new jpeg_destination_mgr;
		jdest->init_destination=this->init_buffer;
		jdest->empty_output_buffer=this->empty_buffer;
		jdest->term_destination=this->term_buffer;
		jdest->next_output_byte=this->buffer;
		jdest->free_in_buffer=width*height*3;
		
		this->cinfo->dest=jdest;
		this->cinfo->image_width=width;
		this->cinfo->image_height=height;
		this->cinfo->input_components=3;
		this->cinfo->in_color_space=JCS_RGB;
		
		jpeg_set_defaults(this->cinfo);
		this->cinfo->optimize_coding=TRUE;
		return;
	};
	
	~JpegCompress()
	{
		delete this->cinfo->err;
		delete this->cinfo->dest;
		delete[] this->buffer;
		delete this->cinfo;
	};
	
	void compress(uint8_t* buffer,int quality,J_DCT_METHOD dct)
	{
		JSAMPROW row_pointer[1];
		this->cinfo->dct_method=dct;
		jpeg_set_quality(this->cinfo,quality,TRUE);
		jpeg_start_compress(this->cinfo,TRUE);
		while(this->cinfo->next_scanline < this->cinfo->image_height)
		{
			row_pointer[0]=&buffer[this->cinfo->next_scanline*this->cinfo->image_width*3];
			jpeg_write_scanlines(this->cinfo,row_pointer,1);
		}
		jpeg_finish_compress(this->cinfo);
	};
	
	size_t getSize()
	{
		return this->cinfo->dest->next_output_byte-this->buffer;
	};
	
	JOCTET* getBuffer()
	{
		return this->buffer;
	};
};

